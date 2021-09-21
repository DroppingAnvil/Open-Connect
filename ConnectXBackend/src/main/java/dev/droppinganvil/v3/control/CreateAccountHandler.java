/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.droppinganvil.v3.Adapters;
import dev.droppinganvil.v3.IPXAccount;

import java.io.IOException;

public class CreateAccountHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        IPXAccount rclient = Adapters.clientjson.fromJson(httpExchange.getRequestBody().toString());
        httpExchange.getResponseBody().write(ClientData.createAccount(rclient, false).getBytes());
    }
}
