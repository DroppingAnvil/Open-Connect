/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal.requests.plans;

import dev.droppinganvil.v2.control.paypal.Link;
import dev.droppinganvil.v2.control.paypal.Plan;

import java.io.Serializable;
import java.util.List;

public class PlanList implements Serializable {
    public List<Plan> plans;
    public Integer total_items;
    public Integer total_pages;
    public List<Link> links;
}
