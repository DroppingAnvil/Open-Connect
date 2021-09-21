/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control.paypal;

import java.io.Serializable;
import java.util.List;

public class PayPalSubscriptions implements Serializable {
    Integer total_items;
    Integer total_pages;
    List<Plan> plans;
}
