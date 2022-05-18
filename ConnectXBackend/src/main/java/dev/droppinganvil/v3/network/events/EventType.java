package dev.droppinganvil.v3.network.events;

public enum EventType {
    DICTIONARY_EDIT,
    /**
     * Resource common to all nodes has been updated
     */
    GLOBAL_RESOURCE_UPDATE,
    /**
     * device joined cx network
     */
    AccountCreate,
    ResourceModification,
    /**
     * Schedules a network restart
     */
    RESTART,
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
