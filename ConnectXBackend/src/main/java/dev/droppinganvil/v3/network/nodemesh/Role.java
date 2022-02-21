package dev.droppinganvil.v3.network.nodemesh;

public enum Role {
    /**
     * Stores persistent service data
     */
    CLOUD,
    /**
     * Stores bulk data for GlobalDrive and/or CloudX
     */
    CLOUD_RESOURCE,
    /**
     * Stores relevant ConnectXContainers. Useful when needing a moderator between a peer to peer connection
     */
    PROXY,
    /**
     * Only stores relevant ConnectXContainers and service data. Can be used for immediate data transfer
     */
    CLIENT,
    /**
     * Stores relevant data for transmitting data through HTTP
     */
    GATEWAY_HTTP,
}
