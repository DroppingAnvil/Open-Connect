/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal.requests.subscriptions;

import dev.droppinganvil.v3.paypal.Link;
import dev.droppinganvil.v3.paypal.WebhookEvent;
import dev.droppinganvil.v3.paypal.requests.Credentials;
import okhttp3.Request;

import java.io.IOException;
import java.io.Serializable;

public class CaptureAuth implements Serializable {
    public Boolean final_capture;
    public static void capture(WebhookEvent we) throws IOException {
        for (Link l : we.links) {
            if (l.rel.equals("capture")) {
                Credentials.client.newCall(new Request.Builder()
                        .url(l.href)
                        .addHeader("Authorization", Credentials.auth)
                        .addHeader("Content-Type", "application/json")
                        .build()
                ).execute();
            }
        }
    }
}
