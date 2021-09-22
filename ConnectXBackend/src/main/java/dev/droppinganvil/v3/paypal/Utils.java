/*
 * Copyright (c) 2021 Twisted Palms Incorporated
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.paypal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static DateFormat format = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ssZ");
    public static String getISOTime() {
        return format.format(new Date());
    }
    public static Long isoToMillis(String iso) throws ParseException {return format.parse(iso).toInstant().toEpochMilli();}
}
