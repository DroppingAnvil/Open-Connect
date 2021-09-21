package dev.droppinganvil.v3.control;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dev.droppinganvil.v3.control.serverauth.ServerKeys;

import java.io.IOException;

public class GetClientHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String serverAuth = exchange.getRequestHeaders().get("server").get(0);
        String clientID = exchange.getRequestHeaders().get("client").get(0);
        if (ServerKeys.keyCache)
    }
}
