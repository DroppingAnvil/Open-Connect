/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal.requests;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;
import dev.droppinganvil.v2.IPXAccount;
import dev.droppinganvil.v2.control.paypal.*;
import dev.droppinganvil.v2.control.paypal.requests.plans.PlanList;
import dev.droppinganvil.v2.control.paypal.requests.products.ProductList;
import dev.droppinganvil.v2.control.paypal.requests.subscriptions.CaptureAuth;
import dev.droppinganvil.v2.control.paypal.requests.subscriptions.CreateSubscriptionBody;

public class ObjectBuilders {
    public static final Moshi moshi = new Moshi.Builder()
            .add(new ObjectBuilders())
            .build();
    public static final JsonAdapter<VerifyWebhookW> webhookJsonAdapter = moshi.adapter(VerifyWebhookW.class).lenient();
    public static final JsonAdapter<WebhookEvent> webhookEJsonAdapter = moshi.adapter(WebhookEvent.class).lenient();
    public static final JsonAdapter<ProductList> productListJsonAdapter = moshi.adapter(ProductList.class).lenient();
    public static final JsonAdapter<Product> productJsonAdapter = moshi.adapter(Product.class).lenient();
    public static final JsonAdapter<Plan> planJSONAdapter = moshi.adapter(Plan.class).lenient();
    public static final JsonAdapter<PlanList> planListJsonAdapter = moshi.adapter(PlanList.class).lenient();
    public static final JsonAdapter<CreateSubscriptionBody> subscriptionJSONAdapter = moshi.adapter(CreateSubscriptionBody.class).lenient();
    public static final JsonAdapter<IPXAccount> clientJsonAdap = moshi.adapter(IPXAccount.class).lenient();
    public static final JsonAdapter<CaptureAuth> captureJson = moshi.adapter(CaptureAuth.class).lenient();
    @FromJson
    public EventType fromJSON(String json){
        return EventType.valueOf(json.replace(".", "_").replace("-", "__"));
    }
    @ToJson
    public String toJSON(EventType eventType) {
        return eventType.name().replace("__", "-").replace("_", ".");
    }
}
