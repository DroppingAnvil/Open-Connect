/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import dev.droppinganvil.v3.edge.ConnectXContainer;
import dev.droppinganvil.v3.edge.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectXAPI {
    public static ConnectXAPI instance;
    public static ConcurrentHashMap<String, ConnectXContainer> clientCache = new ConcurrentHashMap<>();
    public static Platform platform;
    final static Logger logger = LoggerFactory.getLogger(ConnectXAPI.class);

    public ConnectXAPI() throws IOException {

        instance = this;
        String osS = System.getProperty("os.name");
        osS = osS.toLowerCase(Locale.ROOT);
        if (osS.contains("linux")) {
            platform = Platform.LINUX_GENERIC;
        } else if (osS.contains("windows")) {
            platform = Platform.WINDOWS;
        } else if (osS.contains("osx")) {
            platform = Platform.OSX;
        }

    }
    @Deprecated
    public static ConnectXContainer getClient(String id) {
        if (clientCache.containsKey(id)) return clientCache.get(id);
        ConnectXContainer client = ConnectXContainer.requestData(id, serverKey.tempKey);
        clientCache.put(id, client);
        return client;
    }

    public static ConnectXContainer getClient(String id, Boolean mustBeLoggedOn) throws IllegalAccessException {
        if (clientCache.containsKey(id)) return clientCache.get(id);
        if (mustBeLoggedOn) throw new IllegalAccessException();
        ConnectXContainer client = ConnectXContainer.requestData(id, serverKey.tempKey);
        clientCache.put(id, client);
        return client;
    }

    public static Boolean isSubscriptionValid(ConnectXContainer client, Product product) {
        if (client.subscriptions.containsKey(product)) {
            return client.subscriptions.get(product) > System.currentTimeMillis();
        }
        return false;
    }

    public Boolean authenticate(String id, String auth) {
        if (clientCache.containsKey(id)) {
            return clientCache.get(id).id.equals(auth);
        } else {
            //TODO load balancer
            ConnectXContainer cxa = ConnectXContainer.requestData(id, serverKey.tempKey);
            if (cxa == null) return false;
            return cxa.disabled || cxa.key.equals(auth);
        }
    }

    public void login() throws IOException {

    }

    public static class Clients {
        List<ConnectXContainer> clients;
    }
}
