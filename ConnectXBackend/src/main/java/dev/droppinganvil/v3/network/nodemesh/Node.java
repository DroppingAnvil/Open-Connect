package dev.droppinganvil.v3.network.nodemesh;

import java.io.Serializable;
import java.util.List;

public class Node implements Serializable {
    /**
     * Known URLs for initial handshake with a Boolean defining active state
     */
    public List<NodeURL> webURLs;
    /**
     * Primary Identification
     */
    public String primaryKey;
}
