package dev.droppinganvil.v3.secure.userflow;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.droppinganvil.v3.Adapters;
import dev.droppinganvil.v3.IPXAccount;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.UUID;

public class EditUser implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        EditUserRequest eur = Adapters.editrequestjson.fromJson(httpExchange.getRequestBody().toString());
        IPXAccount rU = eur.user;
        if (ProcessX.userCache.containsKey(rU.id)) {
            IPXAccount aru = ProcessX.userCache.get(rU.id);
            if (rU.key.equals(aru.key)) {
                if (!aru.isAdmin) {
                    httpExchange.sendResponseHeaders(403, "Insufficient Permissions".getBytes().length);
                    httpExchange.getResponseBody().write("Insufficient Permissions".getBytes());
                    httpExchange.close();
                    return;
                } else {
                    IPXAccount newUser = eur.newUser;
                    if (eur.removeNew) {
                        if (ProcessX.userCache.containsKey(newUser.id)) {
                            IPXAccount registered = ProcessX.userCache.get(newUser.id);
                            registered.isAdmin = false;
                            registered.key = null;
                            registered.authorization = UUID.randomUUID().toString();
                            registered.disabled = true;
                            try {
                                ProcessX.users.saveData(registered, "id", registered.id);
                                httpExchange.sendResponseHeaders(200, "Account Disabled".getBytes().length);
                                httpExchange.getResponseBody().write("Account Disabled".getBytes());
                                httpExchange.close();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            if (!doesAccountExist(newUser.id)) {
                                String s = validateAccount(newUser);
                                if (s.contains(" ")) {
                                    httpExchange.sendResponseHeaders(400, s.getBytes().length);
                                    httpExchange.getResponseBody().write(s.getBytes());
                                    httpExchange.close();
                                    return;
                                } else {
                                    ProcessX.userCache.put(newUser.id, newUser);
                                    ProcessX.users.saveData(newUser, "id", newUser.id);
                                    httpExchange.sendResponseHeaders(200, "Account Created.".getBytes().length);
                                    httpExchange.getResponseBody().write("Account Created.".getBytes());
                                }

                            }
                            IPXAccount user = ProcessX.userCache.get(newUser.id);
                            for (Field f : IPXAccount.class.getDeclaredFields()) {
                                String fn = f.getName();
                                String old = f.get(user).toString();
                                String neww = f.get(newUser).toString();
                                f.set(user, neww);
                                Logger.log(aru.id + " " + aru.name + " PERFORMED USER EDIT ON USER " + newUser.id + " " +
                                        newUser.name + " " + fn + " " + old + " -> " + neww);
                            }
                            httpExchange.sendResponseHeaders(200, "User edit complete.".getBytes().length);
                            httpExchange.getResponseBody().write("User edit complete".getBytes());
                            httpExchange.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public String validateAccount(IPXAccount dc) throws IllegalAccessException, IOException, InstantiationException {
        if (!dc.subscriptions.isEmpty()) return "Invalid Data - CAF001";
        if (dc.products != null) return "Invalid Data - CAF002";
        if (dc.used_ips != null) return "Invalid Data - CAF003";
        if (dc.name == null || dc.name.toCharArray().length < 4) return "Invalid Name - CAF004";
        if (dc.email == null && dc.id == null) return "Invalid Data - CAF004";
        if (dc.authorization == null || dc.authorization.toCharArray().length < 5)
            return "Invalid Password - Please choose a password greater than 5 characters.";
        if (doesAccountExist(dc.id)) return "Account already exist - CAF006";
        return "";
    }
    public boolean doesAccountExist(String id) throws IllegalAccessException, IOException, InstantiationException {
        if (ProcessX.userCache.containsKey(id)) return true;
        IPXAccount c = ProcessX.users.getObject("id", id, IPXAccount.class);
        if (c != null) {
            ProcessX.userCache.put(id, c);
            return true;
        }
        return false;
    }
}
