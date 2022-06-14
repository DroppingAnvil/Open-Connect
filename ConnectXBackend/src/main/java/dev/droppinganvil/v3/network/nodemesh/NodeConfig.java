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
    public static Integer pThreads = 10;
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
    public static Long ioSocketSleep = 100L;
    public static Integer ioWriteByteBuffer = 2048;
    public static Integer ioReadByteBuffer = 2048;
    public static Integer ioReverseByteBuffer = 2048;
    //TODO
    public static Integer IO_INPUT_SKIP = 2048;
    public static Integer IO_MAX_INPUT = 10000000;
    public static boolean autoUpdate = true;
    public static boolean supportUnavailableServices = false;
    public static boolean devMode = false;
}
