/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v2.control;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;
import dev.droppinganvil.v2.Configuration;
import dev.droppinganvil.v2.EmbeddedAPI;
import dev.droppinganvil.v2.IPXAccount;
import dev.droppinganvil.v2.control.analytics.AnalyticData;
import dev.droppinganvil.v2.control.analytics.Analytics;
import dev.droppinganvil.v2.control.paypal.requests.WebhookHandler;
import dev.droppinganvil.v2.control.serverauth.ServerKeys;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.HashSet;

public class ControlService {
    public static HashSet<String> servers = new HashSet<>();
    private static HttpsServer server;
    public static EmbeddedAPI apiI;

    public static void main(String[] args) throws Exception {
        try {
            // setup the socket address
            InetSocketAddress address = new InetSocketAddress("", 443);

            // initialise the HTTPS server
            HttpsServer httpsServer = HttpsServer.create(address, 0);
            server = httpsServer;
            SSLContext sslContext = SSLContext.getInstance("TLS");

            // initialise the keystore
            char[] password = Configuration.WEBSERVER_SSL_PASS.toCharArray();
            KeyStore ks = KeyStore.getInstance(Configuration.WEBSERVER_SSL_KEYSTORE);
            FileInputStream fis = new FileInputStream(Configuration.WEBSERVER_SSL_CERTNAME);
            ks.load(fis, password);

            // setup the key manager factory
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, password);

            // setup the trust manager factory
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);

            // setup the HTTPS context and parameters
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            httpsServer.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
                public void configure(HttpsParameters params) {
                    try {
                        SSLContext context = getSSLContext();
                        SSLEngine engine = context.createSSLEngine();
                        params.setNeedClientAuth(false);
                        params.setCipherSuites(engine.getEnabledCipherSuites());
                        params.setProtocols(engine.getEnabledProtocols());
                        SSLParameters sslParameters = context.getSupportedSSLParameters();
                        params.setSSLParameters(sslParameters);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });
            try {
                apiI = new EmbeddedAPI();
                //todo remove dev access
                IPXAccount admin = new IPXAccount();
                admin.name = "Dropping Anvil";
                admin.id = "DroppingAnvil";
                admin.authorization = "root";
                admin.email = "DroppingAnvil@";
                admin.isAdmin = true;
                admin.disabled = false;
                ClientData.clientCache.put("DroppingAnvil", admin);
                //todo optimize order for stability
                httpsServer.createContext("/slogin", new ServerKeys());
                httpsServer.createContext("/login", new LoginHandler());
                httpsServer.createContext("/createaccount", new CreateAccountHandler());
                httpsServer.createContext("/clients", )
                //Get PayPal token
                //TODO Setup products
            } catch (Exception e) {
                Logger.log("Failed to start core services");
                Analytics.addData(AnalyticData.Critical_Error, e);
            }
            httpsServer.createContext("/payments", new WebhookHandler());
            httpsServer.setExecutor(null); // creates a default executor
            httpsServer.start();

        } catch (Exception exception) {
            Analytics.addData(AnalyticData.Internal_Error, exception);
            exception.printStackTrace();

        }
    }
}
