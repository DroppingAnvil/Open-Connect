package dev.droppinganvil.v3.network.nodemesh.events;

import java.net.Socket;

public enum EventType {
    GLOBAL_RESOURCE_UPDATE(Socket.class),
    ;

    EventType(Class<?> clazz) {
    }
}
