/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal;

import java.io.Serializable;

public class WebhookVerificationRequest implements Serializable {
    public Verification verification_status;
}
