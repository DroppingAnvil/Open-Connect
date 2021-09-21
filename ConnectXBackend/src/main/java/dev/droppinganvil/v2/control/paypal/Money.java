/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal;

import java.io.Serializable;

public class Money implements Serializable {
    public String currency_code;
    public String value;
    public Details details;
}
