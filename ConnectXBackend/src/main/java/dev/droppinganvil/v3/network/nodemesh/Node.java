package dev.droppinganvil.v3.network.nodemesh;

import java.io.Serializable;
import java.util.List;

public class Node implements Serializable {
    /**
     * HTTP URL for connection
     */
    public String url;
    /**
     * Address for connection
     */
    public List<String> isa;
    /**
     * Device's network account id
     */
    public String networkAccountID;
    /**
     * Node public key
     */
    public String publicKey;
    /**
     * Roles node provides
     */
    public List<Role> roles;
    /**
     * User defined non unique device name
     */
    public String nicename;
}
