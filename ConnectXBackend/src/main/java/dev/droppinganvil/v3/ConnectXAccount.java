/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import me.droppinganvil.core.mysql.annotations.MemoryOnly;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import java.io.IOException;
import java.io.Serializable;
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

    public static OkHttpClient client = new OkHttpClient();
    private static final Moshi moshi = new Moshi.Builder().build();
    public static final JsonAdapter<ConnectXAccount> clientJsonAdapter = moshi.adapter(ConnectXAccount.class).lenient();

    public static ConnectXAccount requestData(String id, String tempKey) {

    }

}
