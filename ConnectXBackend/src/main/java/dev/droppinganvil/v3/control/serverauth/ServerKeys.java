/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control.serverauth;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.droppinganvil.v3.Configuration;
import dev.droppinganvil.v3.EmbeddedAPI;
import dev.droppinganvil.v3.ServerKey;
import dev.droppinganvil.v3.control.analytics.AnalyticData;
import dev.droppinganvil.v3.control.analytics.Analytics;
import me.droppinganvil.core.exceptions.TypeNotSetException;
import me.droppinganvil.core.mysql.MySQL;
import org.eclipse.jetty.util.ConcurrentHashSet;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ServerKeys implements HttpHandler {
    public static ConcurrentHashMap<String, ServerKey> keyCache = new ConcurrentHashMap<>();
    public static ConcurrentHashSet<String> tempKeys = new ConcurrentHashSet<>();
    private static final MySQL keyServer = new MySQL(Configuration.STORAGE_SERVERS_USERNAME, Configuration.STORAGE_SERVERS_PASS, "ServerKeys", Configuration.STORAGE_SERVERS_URL, ServerKey.class, Configuration.STORAGE_SERVERS_SCHEMA);
    public static void loginServer(HttpExchange httpe) {
        try {
            if (getKey(Configuration.INTERNAL_SERVERKEY1) == null) {
                ServerKey dev = new ServerKey();
                dev.primaryKey = Configuration.INTERNAL_SERVERKEY1;
                dev.secondaryKey = Configuration.INTERNAL_SERVERKEY2;
                keyCache.put(Configuration.INTERNAL_SERVERKEY1, dev);
                keyServer.saveData(dev, "primaryKey", Configuration.INTERNAL_SERVERKEY1);
                Analytics.addData(AnalyticData.Server_Key_Creation, dev.primaryKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ServerKey skr = EmbeddedAPI.serverKeyJsonAdapter.fromJson(httpe.getRequestBody().toString());
            ServerKey sk = getKey(skr.primaryKey);
            if (sk == null) return;
            if (skr.secondaryKey.equals(sk.secondaryKey)) {
                //todo verify ip
                sk.tempKey = UUID.randomUUID().toString();
                httpe.getResponseBody().write(EmbeddedAPI.serverKeyJsonAdapter.toJson(sk).getBytes());
            }
        } catch (Exception e) {
            Analytics.addData(AnalyticData.Internal_Error, e);
            e.printStackTrace();
        }
    }
    public static ServerKey getKey(String s) throws IOException, InstantiationException, TypeNotSetException, IllegalAccessException {
        ServerKey sk = keyServer.getObject("primaryKey", s);
        if (sk == null) {
            return null;
        }
        keyCache.put(s, sk);
        return sk;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        loginServer(httpExchange);
    }
}
