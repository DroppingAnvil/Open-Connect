package dev.droppinganvil.v3.network.nodemesh;

public enum Role {
    /**
     * Lowest level of network functionality, participates in CX network functions
     */
    CX,
    /**
     * Node that provides backend services
     */
    BACKEND,
    /**
     * Node host VPN services
     */
    VPN,
    /**
     * Differing from backends servers are to be operated by NUs
     */
    SERVER,
}
