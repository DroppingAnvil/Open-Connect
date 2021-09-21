/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal.requests.subscriptions;

import dev.droppinganvil.v2.control.paypal.ApplicationContext;
import dev.droppinganvil.v2.control.paypal.Link;
import dev.droppinganvil.v2.control.paypal.Money;
import dev.droppinganvil.v2.control.paypal.Subscriber;

import java.io.Serializable;
import java.util.List;

public class CreateSubscriptionBody implements Serializable {
    public String plan_id;
    public String start_time;
    public Integer quantity;
    public Money shipping_amount;
    public Subscriber subscriber;
    public ApplicationContext application_context;
    public String custom_id;
    public List<Link> links;

    public CreateSubscriptionBody(String plan, Subscriber subscriber, ApplicationContext ac, String id) {

    }
}
