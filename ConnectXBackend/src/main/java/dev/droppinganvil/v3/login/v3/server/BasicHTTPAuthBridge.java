package dev.droppinganvil.v3.login.v3.server;

import com.sun.net.httpserver.BasicAuthenticator;
import dev.droppinganvil.v3.ConnectX;

public class BasicHTTPAuthBridge extends BasicAuthenticator {
    private ConnectX api;
    /**
     * Creates a BasicAuthenticator for the given HTTP realm
     *
     * @param realm The HTTP Basic authentication realm
     * @throws NullPointerException if the realm is an empty string
     */
    public BasicHTTPAuthBridge(String realm, ConnectX api) {
        super(realm);
        this.api = api;
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        return api.authenticate(username, password);
    }
}
