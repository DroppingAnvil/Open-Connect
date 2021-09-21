/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control.paypal;

import java.io.Serializable;

public class VerifyWebhookW implements Serializable {
    public String auth_algo;
    public String cert_url;
    public String transmission_id;
    public String transmission_sig;
    public String transmission_time;
    public String webhook_id;
    public WebhookEvent webhook_event;

    public VerifyWebhookW() {}
}
