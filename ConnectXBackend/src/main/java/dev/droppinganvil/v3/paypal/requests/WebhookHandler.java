/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal.requests;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.sun.net.httpserver.HttpHandler;
import dev.droppinganvil.v3.ConnectXAccount;
import dev.droppinganvil.v3.ProcessX.Logger;
import dev.droppinganvil.v3.Product;
import dev.droppinganvil.v3.secure.userflow.ClientData;
import dev.droppinganvil.v3.analytics.AnalyticData;
import dev.droppinganvil.v3.analytics.Analytics;
import dev.droppinganvil.v3.paypal.*;
import dev.droppinganvil.v3.paypal.requests.plans.PlanRequest;
import dev.droppinganvil.v3.paypal.requests.subscriptions.CaptureAuth;
import dev.droppinganvil.v3.paypal.requests.subscriptions.CreateSubscriptionBody;
import dev.droppinganvil.v3.paypal.requests.subscriptions.SubscriptionRequest;
import me.droppinganvil.core.exceptions.TypeNotSetException;
import okhttp3.*;

import java.io.IOException;
import java.text.ParseException;

public class WebhookHandler implements HttpHandler {    public static OkHttpClient client = new OkHttpClient();
    private static final Moshi moshi = new Moshi.Builder().build();
    public static final JsonAdapter<VerifyWebhookW> webhookJsonAdapter = moshi.adapter(VerifyWebhookW.class).lenient();
    public static final JsonAdapter<WebhookEvent> webhookEJsonAdapter = moshi.adapter(WebhookEvent.class).lenient();
    public static final JsonAdapter<WebhookVerificationRequest> webhookVerificationJsonAdapter = moshi.adapter(WebhookVerificationRequest.class).lenient();
    public static void verifyWebhook(com.sun.net.httpserver.HttpExchange he, WebhookEvent we) {
        System.out.println("Verifying Webhook Data");
        VerifyWebhookW verify = new VerifyWebhookW();
        verify.webhook_event = we;
        verify.auth_algo = he.getRequestHeaders().getFirst("PAYPAL-AUTH-ALGO");
        verify.cert_url = he.getRequestHeaders().getFirst("PAYPAL-CERT-URL");
        verify.transmission_id = he.getRequestHeaders().getFirst("PAYPAL-TRANSMISSION-ID");
        verify.transmission_sig = he.getRequestHeaders().getFirst("PAYPAL-TRANSMISSION-SIG");
        verify.transmission_time = he.getRequestHeaders().getFirst("PAYPAL-TRANSMISSION-TIME");
        verify.webhook_id = we.id;
            client.newCall(new Request.Builder().url(Credentials.url+"/v1/notifications/verify-webhook-signature")
                    .addHeader("authorization", Credentials.auth)
                    .post(RequestBody.create(null, webhookJsonAdapter.toJson(verify)))
                    .build()).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Logger.log("IOException while verifying WebHook");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    WebhookVerificationRequest wr = webhookVerificationJsonAdapter.fromJson(response.body().string());
                    if (wr.verification_status == Verification.SUCCESS) {
                        try {
                            handleVerifiedWebhook(we);
                        } catch (Exception e) {
                            Analytics.addData(AnalyticData.Internal_Error, e);
                            e.printStackTrace();
                        }
                    }
                }
            });
    }

    @Override
    public void handle(com.sun.net.httpserver.HttpExchange httpExchange) throws IOException {
        WebhookEvent we = webhookEJsonAdapter.fromJson(httpExchange.getRequestBody().toString());
        verifyWebhook(httpExchange, we);
    }

    public static void handleVerifiedWebhook(WebhookEvent we) throws TypeNotSetException, InstantiationException, IllegalAccessException, IOException, ParseException {
        Resource r = we.resource;
        String[] split = r.id.split("-");
        Product product = Product.valueOf(split[1]);
        ConnectXAccount client = ClientData.getClient(split[2]);

        Logger.log("Webhook: " + we.event_type.name() + " " + we.create_time + " " + we.summary + " " + we.resource.id + " " + client.email);
        switch (we.event_type) {
            //case BILLING_SUBSCRIPTION_PAYMENT_FAILED: client.subscriptions.
            case PAYMENT_AUTHORIZATION_CREATED:
                CaptureAuth.capture(we);
                break;
            case PAYMENT_SALE_COMPLETED:
                Analytics.addData(AnalyticData.Successful_Payment, product);
                if (!product.isSubscription) {
                    client.products.add(product);
                } else {
                    CreateSubscriptionBody csb = SubscriptionRequest.getSubscription(r.id);
                    Plan plan = PlanRequest.cache.get(csb.plan_id);
                    Long valid = Utils.isoToMillis(csb.start_time) + plan.billing_cycles.get(0).frequency.interval_unit.millis;
                    if (client.subscriptions.containsKey(product)) {
                        client.subscriptions.replace(product, valid);
                    } else {
                        client.subscriptions.put(product, valid);
                    }
                }
                Updates.addUpdate(client);
        }
    }
}
