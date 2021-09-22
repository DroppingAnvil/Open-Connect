/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal;

import java.io.Serializable;

public class Resource implements Serializable {
    public String id;
    public String create_time;
    public String update_time;
    public String state;
    public Money amount;


}
