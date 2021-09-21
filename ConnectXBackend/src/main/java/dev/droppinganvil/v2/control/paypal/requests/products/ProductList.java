/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal.requests.products;

import dev.droppinganvil.v2.control.paypal.Product;

import java.io.Serializable;
import java.util.List;

public class ProductList implements Serializable {
    List<Product> products;
    Integer total_items;
    Integer total_pages;

}
