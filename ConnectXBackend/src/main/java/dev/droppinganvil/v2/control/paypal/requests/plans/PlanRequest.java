/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal.requests.plans;

import dev.droppinganvil.v2.Product;
import dev.droppinganvil.v2.control.paypal.*;
import dev.droppinganvil.v2.control.paypal.requests.Credentials;
import dev.droppinganvil.v2.control.paypal.requests.ObjectBuilders;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PlanRequest {
    public static HashMap<Product, Plan> cache = new HashMap<>();
    public static PlanList getPlans(Integer page, Integer pageSize, Product product) throws IOException {
        String productFilter = product != null ? "product_id=PROD-" +product.name() +"&": "";
        Response response =
                Credentials.client.newCall(new Request.Builder()
                        .url(Credentials.url + "/v1/billing/plans?"+productFilter+"page_size="+pageSize+"&page="+page+"&total_required=true")
                        .addHeader("Authorization", Credentials.auth)
                        .addHeader("Content-Type", "application/json")
                        .build()
                ).execute();
        return ObjectBuilders.planListJsonAdapter.fromJson(response.body().string());
    }
    public static Boolean createPlan(Plan plan) throws IOException {
        Response response =
                Credentials.client.newCall(new Request.Builder()
                        .url(Credentials.url + "/v1/billing/plans")
                        .addHeader("Authorization", Credentials.auth)
                        .addHeader("Content-Type", "application/json")
                        .post(RequestBody.create(null, ObjectBuilders.planJSONAdapter.toJson(plan)))
                        .build()
                ).execute();
        //todo bypass
        //return response.isSuccessful();
        if (!response.isSuccessful()) {
            System.out.println(response.body().string());
        }
        return true;
    }
    public static Plan generateStandardPlan(Product product) {
        Plan p = new Plan();
        p.id = "PLAN-"+product.name();
        p.name = product.name() + " Plan";
        p.description = product.name() + " basic plan";
        p.product_id = "PROD-"+product.name();
        p.status = PlanStatus.ACTIVE;
        p.billing_cycles = new ArrayList<>();
        BillingCycle billing = new BillingCycle();
        billing.frequency = new Frequency();
        billing.pricing_scheme = new PricingScheme();
        billing.pricing_scheme.fixed_price = new FixedPrice();
        billing.pricing_scheme.fixed_price.currency_code = "USD";
        billing.pricing_scheme.fixed_price.value = product.price;
        billing.frequency.interval_count = 1;
        billing.frequency.interval_unit = Interval.MONTH;
        billing.tenure_type = TenureType.REGULAR;
        billing.total_cycles = 0;
        p.billing_cycles.add(billing);
        p.payment_preferences = new PaymentPreference();
        p.payment_preferences.auto_bill_outstanding = true;
        p.payment_preferences.payment_failure_threshold = 1;
        p.payment_preferences.setup_fee = new Money();
        p.payment_preferences.setup_fee.currency_code = "USD";
        p.payment_preferences.setup_fee.value = "0.00";
        p.quantity_supported = false;
        p.taxes = new Taxes();
        p.taxes.inclusive = false;
        p.taxes.percentage = 8.25;
        return p;
    }
}
