/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import dev.droppinganvil.v3.ProcessX.Container;
import me.droppinganvil.core.mysql.annotations.MemoryOnly;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectXAccount implements Serializable {
    public String id;
    public String email;
    public String name;
    public List<String> used_ips;
    public Map<String, Product> products;
    public String authorization;
    @MemoryOnly
    public String key;
    public String payerid;
    public Boolean isAdmin = false;
    public String discordID;
    public Map<Product, Long> subscriptions = new HashMap<>();
    public Boolean disabled = false;
    public List<Container> ownedContainers = new ArrayList<>();

    public static OkHttpClient client = new OkHttpClient();
    private static final Moshi moshi = new Moshi.Builder().build();
    public static final JsonAdapter<ConnectXAccount> clientJsonAdapter = moshi.adapter(ConnectXAccount.class).lenient();

    public static ConnectXAccount requestData(String id, String tempKey) {
        System.out.println("Making request to central server to retrieve client " + id);
        try {
            ConnectXAccount dclient = clientJsonAdapter.fromJson(client.newCall(new Request.Builder().url(Configuration.INTERNAL_CENTRAL_URL + "clients")
                    .addHeader("client", id)
                    .addHeader("server", tempKey)
                    .build()).execute().body().string());
            System.out.println("Response received for client " + id);
            return dclient;
        } catch (IOException e) {
            System.out.print(e);
            return null;
        }
    }

}
