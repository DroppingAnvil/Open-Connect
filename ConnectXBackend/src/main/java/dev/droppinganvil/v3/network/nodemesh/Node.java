package dev.droppinganvil.v3.network.nodemesh;

import java.io.Serializable;
import java.util.List;

public class Node implements Serializable {
    /**
     * Known URLs for initial handshake
     */
    public List<NodeURL> webURLs;
    /**
     * Public Key
     */
    public String primaryKey;
    /**
     * Public Key
     */
    public String name;
}
