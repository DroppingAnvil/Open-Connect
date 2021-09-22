/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal;

import java.io.Serializable;

public class ApplicationContext implements Serializable {
    public String brand_name;
    public String locale;
    public ShippingPreference shipping_preference;
    public UserAction user_action;
    public PaymentMethod payment_method;
    public String return_url;
    public String cancel_url;
}
