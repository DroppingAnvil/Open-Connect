package dev.droppinganvil.v3.network.nodemesh;

public enum Role {
    /**
     * Stores a copy of all ConnectXContainers, Helps network communicate.
     */
    CLOUD,
    /**
     * Stores data for GlobalDrive and/or CloudX
     */
    CLOUD_RESOURCE,
    /**
     * Stores a copy of all ConnectXContainers, Communicates with outside networks
     */
    GATEWAY,
    /**
     * Only stores relevant ConnectXContainers and service data, Lower verification requirements, Lower security
     */
    CLIENT,
}
