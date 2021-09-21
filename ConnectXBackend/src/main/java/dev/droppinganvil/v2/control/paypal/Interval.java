/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control.paypal;

public enum Interval {
    DAY(86400000L),
    WEEK(604800000L),
    MONTH(2592000000L),
    YEAR(31104000000L),
    ;

    Interval(Long i) {
        millis = i;
    }
    public Long millis;
}
