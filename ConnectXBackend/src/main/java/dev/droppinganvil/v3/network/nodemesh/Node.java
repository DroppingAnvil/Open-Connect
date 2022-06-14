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
    public String isa;
    /**
     * Device's network account id
     */
    public String cxID;
    /**
     * Node public key
     */
    public String publicKey;
    /**
     * public key's ID
     */
    public Long keyID;
    /**
     * Roles node provides
     */
    public List<String> roles;
    /**
     * User defined non unique device name
     */
    public String nicename;
    /**
     * For future use RESERVED
     */
    public String pr;

    public static boolean validate(Node node) {
        assert node.cxID != null;
        if (node.cxID.length() > 36) return false;
        assert node.publicKey != null;
        return true;
    }
}
