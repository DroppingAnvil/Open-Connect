/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.droppinganvil.v3.Adapters;
import dev.droppinganvil.v3.secure.userflow.ClientData;
import dev.droppinganvil.v3.ConnectXAccount;
import dev.droppinganvil.v3.LoginForm;

import java.io.IOException;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String s;
        LoginForm lf = Adapters.loginFormjson.fromJson(httpExchange.getRequestBody().toString());
        ConnectXAccount client = ClientData.login(lf.id, lf.auth);
        if (client != null) {
            s = client.key;
        } else {
            s = "Invalid Login";
        }
        httpExchange.getResponseBody().write(s.getBytes());
    }
}
