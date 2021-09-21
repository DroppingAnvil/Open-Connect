/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control.paypal;

import java.io.Serializable;

public class BillingCycle implements Serializable {
    public Frequency frequency;
    public TenureType tenure_type;
    public Integer sequence;
    public Integer total_cycles;
    public PricingScheme pricing_scheme;
}
