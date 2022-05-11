/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

public class NodeConfig {
    /**
     * Handles network input
     */
    public static Integer iThreads = 2;
    /**
     * Handles IO (Crypt)
     */
    public static Integer ioThreads = 4;
    public static final Integer rateLimit = 15;
    public static final Integer rateLimitSleep = 1000;
    public static boolean encryptAllResources = false;
    public static boolean signAllResources = true;
    //
    public static Long IO_THREAD_SLEEP = 100L;
    public static Integer IO_WRITE_BYTE_BUFFER = 2048;
    public static Integer IO_READ_BYTE_BUFFER = 2048;
    public static Integer IO_REVERSE_BYTE_BUFFER = 2048;
    //TODO
    public static Integer IO_INPUT_SKIP = 2048;
    public static Integer IO_MAX_INPUT = 10000000;
    public
}
