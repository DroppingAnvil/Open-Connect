/*
 * Copyright (c) 2022. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.nodemesh;

public interface MessageHandler {
    void inputMessage(String message, Node node);
}
