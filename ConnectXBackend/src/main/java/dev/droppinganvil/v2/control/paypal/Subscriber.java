/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal;

import java.io.Serializable;

public class Subscriber implements Serializable {
    public Name name;
    public String email_address;
    public String payer_id;
}
