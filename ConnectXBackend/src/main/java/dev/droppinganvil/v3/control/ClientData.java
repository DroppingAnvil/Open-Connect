/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control;

import dev.droppinganvil.v3.Configuration;
import dev.droppinganvil.v3.IPXAccount;
import me.droppinganvil.core.exceptions.TypeNotSetException;
import me.droppinganvil.core.mysql.MySQL;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ClientData {
    public static final MySQL clientServer = new MySQL(Configuration.STORAGE_USER_USERNAME, Configuration.STORAGE_USER_PASS, "Users", Configuration.STORAGE_USER_URL, IPXAccount.class, Configuration.STORAGE_USER_SCHEMA);
    public static ConcurrentHashMap<String, IPXAccount> clientCache = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, IPXAccount> noauthclientCache = new ConcurrentHashMap<>();

    public static IPXAccount getClient(String id, Boolean mustBeLoggedOn) throws IOException, InstantiationException, TypeNotSetException, IllegalAccessException {
        if (clientCache.containsKey(id)) return clientCache.get(id);
        if (noauthclientCache.containsKey(id)) return noauthclientCache.get(id);
        if (mustBeLoggedOn) throw new IllegalAccessException();
        IPXAccount controlClient;
        if (id.contains("@")) {
            controlClient = clientServer.getObject("email", id);
        } else {
            controlClient = clientServer.getObject("id", id);
        }
        if (controlClient == null) return null;
        clientCache.put(id, controlClient);
        return controlClient;
    }

    public static Boolean doesAccountExist(String id) throws TypeNotSetException, InstantiationException, IllegalAccessException, IOException {
        return getClient(id, false) != null;
    }

    public static String createAccount(IPXAccount client, Boolean throughExternalAuth) {
        try {
            if (!client.subscriptions.isEmpty()) return "Invalid Data - CAF001";
            if (client.products != null) return "Invalid Data - CAF002";
            if (client.used_ips != null) return "Invalid Data - CAF003";
            if (client.name == null || client.name.toCharArray().length < 4) return "Invalid Name - CAF004";
            if (client.email == null && client.id == null) return "Invalid Data - CAF004";
            if (client.authorization == null || client.authorization.toCharArray().length < 5)
                return "Invalid Password - Please choose a password greater than 5 characters.";
            if (doesAccountExist(client.id)) return "Account already exist - CAF006";
            if (client.id != null && !throughExternalAuth) return "Invalid Data - CAF007";
            if (doesAccountExist(client.email)) return "Invalid Data - CAF008";
            if (client.id == null) client.id = UUID.randomUUID().toString();
            clientCache.put(client.id, client);
            if (throughExternalAuth) {
                clientServer.saveData(client, "id", client.id);
                return "Accounted Created! Please note you can only log in through your external auth and will be required to add an email to subscribe or purchase products.";
            } else {
                clientServer.saveData(client, "email", client.email);
                return "Account Created! Please note you can only log in through your email";
            }
        } catch (TypeNotSetException | InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        return "An internal error has occurred - CAF009";
    }

    public static IPXAccount login(String id, String auth) {
        try {
            if (!doesAccountExist(id)) return null;
            IPXAccount client = getClient(id, false);
            if (clientCache.containsKey(client.id)) {
                clientCache.replace(client.id, client);
            } else {
                clientCache.put(id, client);
            }
            if (!client.authorization.equals(auth)) return null;
            String uuid = UUID.randomUUID().toString();
            client.key = uuid;
            IPXAccount level2 = new IPXAccount();
            for (Field f : IPXAccount.class.getDeclaredFields()) {
                if (!f.getName().equals("authorization")) {
                    f.set(level2, f.get(client));
                }
            }
            noauthclientCache.put(id, level2);
            return level2;
        } catch (TypeNotSetException | InstantiationException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
