/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal.requests.plans;

import dev.droppinganvil.v3.paypal.Link;
import dev.droppinganvil.v3.paypal.Plan;

import java.io.Serializable;
import java.util.List;

public class PlanList implements Serializable {
    public List<Plan> plans;
    public Integer total_items;
    public Integer total_pages;
    public List<Link> links;
}
