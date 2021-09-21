/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

public class Product {
    public Product(String name, long price, boolean isSubscription) {
        this.name = name;
        this.price = price;
        this.isSubscription = isSubscription;
    }

    public Product(String name, long price, boolean isSubscription, String extraData) {
        this.name = name;
        this.price = price;
        this.isSubscription = isSubscription;
        this.extraData = extraData;
    }
    //Basic(1.00, true),
    //Premium(5.00, true),
    //Baller(15.00, true),
    //;

    //Product(double v, boolean b) {
    //    price = v;
    //    isSubscription = b;
    //}
    public String name;
    public long price;
    public boolean isSubscription;
    public String extraData;
}
