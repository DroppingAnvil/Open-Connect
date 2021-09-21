/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal;

import java.io.Serializable;

public class Address implements Serializable {
    String address_line_1;
    String address_line_2;
    String admin_area_2;
    String admin_area_1;
    String postal_code;
    String country_code;
}
