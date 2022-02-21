package dev.droppinganvil.v3.network.nodemesh.events;

public enum EventType {
    /**
     * Resource common to all nodes has been updated, in ConnectX Managed it must be sent from NMI or NAS
     */
    GLOBAL_RESOURCE_UPDATE,
    /**
     * A device account request
     */
    ACCOUNT_CREATE,
    /**
     * Device account is created, in ConnectX Managed it must be sent from NAS
     */
    ACCOUNT_CREATED,
    /**
     * Device account has been updated
     */
    ACCOUNT_UPDATE,
    /**
     * "Temporary" node shutdown (Can only be sent from NMI)
     */
    SIGTERM,
    /**
     * Used when transmitting MessageX service data
     */
    SERVICE_MESSAGEX,
    /**
     * Used when transmitting initial data
     */
    HELLO_WORLD,
    ;
}
