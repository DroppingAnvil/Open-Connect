/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import java.io.Serializable;

public class Configuration implements Serializable {
    public static String SDF_FORMAT = "S-m-H-a-EEE-F-M-y";
    public static String netID = "NETWORK";
    public static Boolean active = true;
    public static final Integer rateLimit = 15;
    public static final Integer rateLimitSleep = 1000;
    //
    public static Long IO_THREAD_SLEEP = 100L;
    public static Integer IO_WRITE_BYTE_BUFFER = 1024;
    public static Integer IO_REVERSE_BYTE_BUFFER = 1024;

}
