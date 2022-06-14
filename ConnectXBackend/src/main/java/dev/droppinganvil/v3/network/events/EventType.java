package dev.droppinganvil.v3.network.events;

public enum EventType {
    DICTIONARYEDIT,
    /**
     * Resource common to all nodes has been updated
     */
    GLOBALRESOURCEUPDATE,
    /**
     * device joined cx network
     */
    NewNode,
    ResourceModification,
    /**
     * Schedules a network restart
     */
    RESTART,
    /**
     * Used when transmitting initial data
     */
    HELLOWORLD,
    /**
     * Internal Messaging
     */
    MESSAGE,
    ;
}
