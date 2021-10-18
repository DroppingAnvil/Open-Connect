package dev.droppinganvil.v3.network.nodemesh;

import java.io.Serializable;
import java.util.List;

public class Node implements Serializable {
    /**
     * URL for connection
     */
    public String url;
    /**
     * Address for connection
     */
    public String isa;
    /**
     * Device's network account id
     */
    public String networkAccountID;
    /**
     * Total storage volume available
     */
    public Long capacity;
    /**
     * Total storage volume
     */
    public Long maxCapacity;
    /**
     * Roles node provides
     */
    public List<Role> roles;
}
