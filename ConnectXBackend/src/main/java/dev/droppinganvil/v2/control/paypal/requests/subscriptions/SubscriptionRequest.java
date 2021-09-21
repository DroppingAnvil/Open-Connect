/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal.requests.subscriptions;

import com.sun.net.httpserver.HttpExchange;
import dev.droppinganvil.v2.control.analytics.AnalyticData;
import dev.droppinganvil.v2.control.analytics.Analytics;
import dev.droppinganvil.v2.control.paypal.requests.Credentials;
import dev.droppinganvil.v2.control.paypal.requests.ObjectBuilders;
import okhttp3.*;

import java.io.IOException;

public class SubscriptionRequest {
    public static CreateSubscriptionBody getSubscription(String id) throws IOException {
        Response response =
                Credentials.client.newCall(new Request.Builder()
                        .url(Credentials.url + "/v1/billing/subscriptions/"+id)
                        .addHeader("Authorization", Credentials.auth)
                        .addHeader("Content-Type", "application/json")
                        .build()
                ).execute();
        return ObjectBuilders.subscriptionJSONAdapter.fromJson(response.body().string());
    }
    public static void createSubscription(CreateSubscriptionBody csb, HttpExchange httpe) throws IOException {
                Credentials.client.newCall(new Request.Builder()
                        .url(Credentials.url + "/v1/billing/subscriptions")
                        .addHeader("Authorization", Credentials.auth)
                        .addHeader("Content-Type", "application/json")
                        .post(RequestBody.create(null, ObjectBuilders.subscriptionJSONAdapter.toJson(csb)))
                        .build()
                ).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Analytics.addData(AnalyticData.Internal_Error, e);
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        CreateSubscriptionBody csbr = ObjectBuilders.subscriptionJSONAdapter.fromJson(response.body().string());
                        httpe.getResponseBody().write(csbr.links.get(0).href.getBytes());
                        httpe.close();
                    }
                });
    }
}
