/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control.paypal;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.io.Serializable;
import java.util.List;

public class Plan implements Serializable {
    public String id;
    public String product_id;
    public PlanStatus status;
    public String name;
    public String description;
    public List<BillingCycle> billing_cycles;
    public PaymentPreference payment_preferences;
    public Taxes taxes;
    public Boolean quantity_supported;

    @FromJson
    PlanStatus fromJSON(String json) {
        return PlanStatus.valueOf(json);
    }
    @ToJson
    String toJSON(PlanStatus ps) {
        return ps.name();
    }
}
