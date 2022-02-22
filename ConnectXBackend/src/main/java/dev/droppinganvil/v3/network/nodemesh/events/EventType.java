package dev.droppinganvil.v3.network.nodemesh.events;

public enum EventType {
    /**
     * Used when
     */
    DICTOINARY_EDIT,
    /**
     * Resource common to all nodes has been updated
     */
    GLOBAL_RESOURCE_UPDATE,
    /**
     * A Node has submitted their generated public key and ID
     */
    ACCOUNT_CREATE,
    /**
     * New Node is recognized and their data is submitted
     */
    ACCOUNT_CREATED,
    /**
     * Schedules a network restart
     */
    RESTART,
    /**
     * Used when transmitting MessageX service data
     */
    SERVICE_MESSAGEX,
    /**
     * Used when transmitting initial data
     */
    HELLO_WORLD,
    /**
     * Internal Messaging
     */
    MESSAGE,
    ;
}
