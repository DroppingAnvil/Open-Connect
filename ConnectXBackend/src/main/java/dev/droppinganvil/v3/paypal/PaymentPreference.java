/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal;

import java.io.Serializable;

public class PaymentPreference implements Serializable {
    public Boolean auto_bill_outstanding;
    public Money setup_fee;
    public SetupFeeFailureAction setup_fee_failure_action;
    public Integer payment_failure_threshold;
}
