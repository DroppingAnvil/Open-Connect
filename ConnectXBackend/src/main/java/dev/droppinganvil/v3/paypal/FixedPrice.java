/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal;

import java.io.Serializable;

public class FixedPrice implements Serializable {
    public double value;
    public String currency_code;
}
