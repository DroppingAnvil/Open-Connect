/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal;

import java.io.Serializable;

public class FailedPayment implements Serializable {
    public Money amount;
    public String time;
    public Reason reason;
    public String next_payment_retry_time;
}
