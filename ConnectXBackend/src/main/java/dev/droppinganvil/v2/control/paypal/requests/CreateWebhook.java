/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal.requests;

import dev.droppinganvil.v2.control.paypal.EventType;

import java.io.Serializable;
import java.util.List;

public class CreateWebhook implements Serializable {
    public String url;
    public List<EventType> event_types;
}
