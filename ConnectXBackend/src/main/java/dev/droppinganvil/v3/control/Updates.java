/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.droppinganvil.v3.IPXAccount;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Updates implements HttpHandler {
    //todo possible security vuln
    public static ConcurrentHashMap<String, IPXAccount> updates = new ConcurrentHashMap<>();

    public static void addUpdate(IPXAccount client) {
        for (String s : ControlService.servers) {
            updates.put(s, client);
        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

    }
}
