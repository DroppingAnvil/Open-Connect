package dev.droppinganvil.v3.network.nodemesh;

import java.io.Serializable;

public class NodeURL implements Serializable {
    /**
     * URL for initial handshake
     */
    public String url;
    /**
     * Returns if the system should attempt handshakes with the URL
     */
    public Boolean active;
    /**
     * Use when ordering URLs, accepts any Integer
     */
    public Integer weight;
}
