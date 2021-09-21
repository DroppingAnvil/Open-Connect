/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okhttp3.OkHttpClient;

import java.util.List;

public class Updater implements Runnable {
    public static final JsonAdapter<ConnectXAPI.Clients> clientsJsonAdapter = moshi.adapter(ConnectXAPI.Clients.class).lenient();
    public static OkHttpClient client = new OkHttpClient();
    public static final Moshi moshi = new Moshi.Builder().build();
    private final ConnectXAPI api;

    public Updater(ConnectXAPI api) {
        this.api = api;
    }

    @Override
    public void run() {
        Logger.log("Update thread started");
        while (true) {
            List<ConnectXAccount> clients = api.getUpdates();
            if (clients != null) {
                int complete = 0;
                int updates = clients.size();
                Logger.log("Updates received from control server. Applying updates 0/"+updates);
                for (ConnectXAccount dadc : clients) {
                    complete = complete + 1;
                    Logger.dlog("Applying updates 0/" + complete);
                    if (ConnectXAPI.clientCache.containsKey(dadc.id)) {
                        ConnectXAPI.clientCache.replace(dadc.id, dadc);
                    } else {
                        Logger.dlog("Update 0/" + complete + " was ignored, not in cache");
                    }
                }
                Logger.log("Updates complete");
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
