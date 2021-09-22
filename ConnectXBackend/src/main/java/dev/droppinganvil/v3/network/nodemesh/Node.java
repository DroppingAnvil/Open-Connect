package dev.droppinganvil.v3.network.nodemesh;

import me.droppinganvil.core.mysql.annotations.MemoryOnly;

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
    public String publicKey;
    /**
     * Only present when initializing device
     */
    @MemoryOnly
    public String privateKey;
    /**
     * Device ID
     */
    public String deviceID;
    /**
     * Friendly name
     */
    public String name;
}
