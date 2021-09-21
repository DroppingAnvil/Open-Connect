/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control.paypal;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.io.Serializable;

public class Frequency implements Serializable {
    public Interval interval_unit;
    public Integer interval_count;
    @FromJson
    Interval fromJSON(String json) {
        return Interval.valueOf(json);
    }
    @ToJson
    String toJSON(Interval ps) {
        return ps.name();
    }
}
