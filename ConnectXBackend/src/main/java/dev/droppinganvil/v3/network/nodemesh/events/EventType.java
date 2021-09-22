package dev.droppinganvil.v3.network.nodemesh.events;

import java.net.Socket;

public enum EventType {
    /**
     * Resource common to all nodes has been updated
     */
    GLOBAL_RESOURCE_UPDATE,
    /**
     * Device provides network node information
     */
    DEVICE_HANDSHAKE1,
    /**
     * System encrypts data using device's public key and sends to device
     */
    DEVICE_HANDSHAKE2,
    /**
     * Device sends decrypted data to result in verification success
     */
    DEVICE_VERIFIED,
    ;
}
