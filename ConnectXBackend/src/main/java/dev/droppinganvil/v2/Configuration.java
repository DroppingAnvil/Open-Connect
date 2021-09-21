/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Configuration implements Serializable {

    //IPX
    public static String SDF_FORMAT = "S-m-H-a-EEE-F-M-y";
    public static String domain = "anvildevelopment.us";
    public static String serverID = "na1";
    public static String control = "na1";
    public static final Integer rateLimit = 15;
    public static final Integer rateLimitSleep = 1000;
    public static boolean controlServer = true;
    public static boolean handleAPI = true;
    public static List<String> peers = new ArrayList<>();
    public static String WEBSERVER_HTTPS_BIND_HOSTNAME = "";
    public static Integer WEBSERVER_HTTPS_BIND_PORT = 443;
    public static String WEBSERVER_SSL_KEYSTORE = "JKS";
    public static String WEBSERVER_SSL_CERTNAME = "Default.jks";
    public static String WEBSERVER_SSL_PASS = "faeb178f-1f6b-4975-99d8-302b2f0bd370";
    public static String STORAGE_USER_URL = "mysqlx://localhost:33060";
    public static String STORAGE_USER_SCHEMA = "hosting";
    public static String STORAGE_USER_USERNAME = "Control";
    public static String STORAGE_USER_PASS = "faeb178f-1f6b-4975-99d8-302b2f0bd370";
    public static String STORAGE_PAYMENT_URL = "mysqlx://localhost:33060";
    public static String STORAGE_PAYMENT_SCHEMA = "hosting";
    public static String STORAGE_PAYMENT_USERNAME = "Control";
    public static String STORAGE_PAYMENT_PASS = "faeb178f-1f6b-4975-99d8-302b2f0bd370";

    //TODO remove after dev
    public static String INTERNAL_SERVERKEY1;
    public static String INTERNAL_SERVERKEY2;


    /**
     * The central server is the controller for all other nodes, it is recommended to only allow connections from ProjectServers through your firewall
     */
    public static String INTERNAL_CENTRAL_URL = "https://"+control+"."+domain+"/";
    //CRYPT
    public static String CRYPT_BASE_PASS = "faeb178f-1f6b-4975-99d8-302b2f0bd370";
    public static String CRYPT_CONNECT_URL = "https://na1.droppinganvil.dev/publickey.txt";
    /**
     * No spaces
     */
    public static String CRYPT_CONNECT_ORG = "DroppingAnvilDevelopment";

    //RemoteDirectory
    public static Long IO_THREAD_SLEEP = 100L;
    public static Integer IO_WRITE_BYTE_BUFFER = 1024;
    public static Integer IO_REVERSE_BYTE_BUFFER = 1024;

}
