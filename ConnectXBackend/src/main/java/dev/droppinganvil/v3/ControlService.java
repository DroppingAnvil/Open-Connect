/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3;

import com.sun.net.httpserver.HttpsServer;
import dev.droppinganvil.v3.analytics.AnalyticData;
import dev.droppinganvil.v3.analytics.Analytics;
import dev.droppinganvil.v3.edge.ClientData;
import dev.droppinganvil.v3.edge.ConnectXContainer;

import java.util.HashSet;

import static dev.droppinganvil.v3.ConnectXAPI.logger;

public class ControlService {
    public static HashSet<String> servers = new HashSet<>();
    public static ConnectXAPI apiI;
    public static HttpsServer server;

    public static void main(String[] args) throws Exception {
        try {
            logger.info("Starting ConnectX instance");
            try {
                apiI = new ConnectXAPI();
                //todo remove dev access
                ConnectXContainer admin = new ConnectXContainer();
                admin.name = "Dropping Anvil";
                admin.id = "DroppingAnvil";
                admin.authorization = "root";
                admin.email = "DroppingAnvil@";
                admin.isAdmin = true;
                admin.disabled = false;
                ClientData.clientCache.put("DroppingAnvil", admin);
                //todo optimize order for stability
                //Get PayPal token
                //TODO Setup products
            //TODO httpsServer.createContext("/payments", new WebhookHandler());
        } catch (Exception exception) {
            Analytics.addData(AnalyticData.Internal_Error, exception);
            exception.printStackTrace();

        }
    }
}
