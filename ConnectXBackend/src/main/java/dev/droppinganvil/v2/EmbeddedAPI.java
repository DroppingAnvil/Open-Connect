/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v2;

import com.squareup.moshi.JsonAdapter;
import dev.droppinganvil.v2.control.Platform;
import dev.droppinganvil.v2.keychange.LoginServer;
import me.droppinganvil.core.mysql.MySQL;
import okhttp3.Request;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

public class EmbeddedAPI {
    public static MySQL productServer = new MySQL(Configuration.STORAGE_PAYMENT_USERNAME,Configuration.STORAGE_PAYMENT_PASS,"products",Configuration.STORAGE_PAYMENT_URL, Configuration.STORAGE_PAYMENT_SCHEMA);
    public static EmbeddedAPI instance;
    public static ServerKey serverKey;
    private static String serverID;
    public static JsonAdapter<ServerKey> serverKeyJsonAdapter = Updater.moshi.adapter(ServerKey.class).lenient();
    public static ConcurrentHashMap<String, IPXAccount> clientCache = new ConcurrentHashMap<>();
    public static Platform platform;

    public EmbeddedAPI() throws IOException {
        serverKey = new ServerKey();
        serverKey.primaryKey = Configuration.INTERNAL_SERVERKEY1;
        serverKey.secondaryKey = Configuration.INTERNAL_SERVERKEY2;
        LoginServer.login(serverKey);
        serverID = Configuration.serverID;
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
    public static IPXAccount getClient(String id) {
        if (clientCache.containsKey(id)) return clientCache.get(id);
        IPXAccount client = IPXAccount.requestData(id, serverKey.tempKey);
        clientCache.put(id, client);
        return client;
    }

    public static IPXAccount getClient(String id, Boolean mustBeLoggedOn) throws IllegalAccessException {
        if (clientCache.containsKey(id)) return clientCache.get(id);
        if (mustBeLoggedOn) throw new IllegalAccessException();
        IPXAccount client = IPXAccount.requestData(id, serverKey.tempKey);
        clientCache.put(id, client);
        return client;
    }

    public static Boolean isSubscriptionValid(IPXAccount client, Product product) {
        if (client.subscriptions.containsKey(product)) {
            return client.subscriptions.get(product) > System.currentTimeMillis();
        }
        return false;
    }

    public List<IPXAccount> getUpdates() {
        System.out.println("Making request to central server to retrieve updates");
        try {
            Clients clients = Updater.clientsJsonAdapter.fromJson(Updater.client.newCall(new Request.Builder().url(Configuration.INTERNAL_CENTRAL_URL + "updates")
                    .addHeader("server", serverKey.tempKey)
                    .build()).execute().body().string());
            System.out.println("Response received for ");
            return clients.clients;
        } catch (IOException e) {
            System.out.print(e);
            return null;
        }
    }

    public static class Clients {
        List<IPXAccount> clients;
    }
}
