package dev.droppinganvil.v3.network.nodemesh;

public enum Role {
    /**
     * ConnectX nodes that transfer data between ConnectX networks
     */
    CX_GATEWAY,
    /**
     * Nodes that will not assist in network functionality
     */
    CLIENT,
    /**
     * Nodes that are most likely end users but will assist in replication
     */
    CLIENT_NODE,
    /**
     * Nodes that provide ConnectX services
     */
    BACKEND,
}
