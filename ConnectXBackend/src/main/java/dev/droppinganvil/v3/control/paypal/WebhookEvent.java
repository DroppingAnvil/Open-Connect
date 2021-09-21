/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control.paypal;

import java.io.Serializable;
import java.util.List;

public class WebhookEvent implements Serializable {
    public String id;
    public String create_time;
    public String resource_type;
    public String event_version;
    public EventType event_type;
    public String summary;
    public String resource_version;
    public Resource resource;
    public List<Link> links;
}
