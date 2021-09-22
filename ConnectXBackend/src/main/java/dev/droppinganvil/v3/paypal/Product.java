/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal;

import java.io.Serializable;

public class Product implements Serializable {
    public String id;
    public String name;
    public String description;
    public ProductType type;
    public String image_url;
    public String home_url;
    public String create_time;
    public String update_time;

}
