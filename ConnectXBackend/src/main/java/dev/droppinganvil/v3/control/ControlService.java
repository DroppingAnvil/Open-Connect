/*
 * Copyright (c) 2021 Christopher Willett
 * All Rights Reserved.
 */

package dev.droppinganvil.v3.control;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;
import dev.droppinganvil.v3.Configuration;
import dev.droppinganvil.v3.EmbeddedAPI;
import dev.droppinganvil.v3.IPXAccount;
import dev.droppinganvil.v3.control.analytics.AnalyticData;
import dev.droppinganvil.v3.control.analytics.Analytics;
import dev.droppinganvil.v3.control.paypal.requests.WebhookHandler;
import dev.droppinganvil.v3.control.serverauth.ServerKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.HashSet;

public class ControlService {
    public static HashSet<String> servers = new HashSet<>();
    public static EmbeddedAPI apiI;
    final static Logger logger = LoggerFactory.getLogger(ControlService.class);
    public static HttpServer server;

    public static void main(String[] args) throws Exception {
        try {
            logger.info("Starting ConnectX instance");
            InetSocketAddress address = new InetSocketAddress("", 443);
            logger.debug("Server address obtained");
            HttpsServer httpsServer = HttpsServer.create(address, 0);
            logger.debug("Created HTTPS Server");
            logger.info("Setting up HTTPS");
            SSLContext sslContext = SSLContext.getInstance("TLS");
            KeyStore ks = KeyStore.getInstance(Configuration.WEBSERVER_SSL_KEYSTORE);
            FileInputStream fis = new FileInputStream(Configuration.WEBSERVER_SSL_CERTNAME);
            ks.load(fis, Configuration.WEBSERVER_SSL_PASS.toCharArray());
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(ks, Configuration.WEBSERVER_SSL_PASS.toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(ks);
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
                httpsServer.createContext("/api/v3/slogin", new ServerKeys());
                httpsServer.createContext("/api/v3/login", new LoginHandler());
                httpsServer.createContext("/api/v3/createaccount", new CreateAccountHandler());
                httpsServer.createContext("/api/v3/clients", )
                //Get PayPal token
                //TODO Setup products
            } catch (Exception e) {
                logger.error("");
                Analytics.addData(AnalyticData.Critical_Error, e);
            }
            httpsServer.createContext("/payments", new WebhookHandler());
            httpsServer.setExecutor(null); // creates a default executor
            httpsServer.start();
            server = httpsServer;
        } catch (Exception exception) {
            Analytics.addData(AnalyticData.Internal_Error, exception);
            exception.printStackTrace();

        }
    }
}
