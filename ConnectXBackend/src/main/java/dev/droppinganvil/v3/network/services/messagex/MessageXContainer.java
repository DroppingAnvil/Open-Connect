/*
 * Copyright (c) 2021. Christopher Willett
 * All Rights Reserved
 */

package dev.droppinganvil.v3.network.services.messagex;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MessageXContainer implements Serializable {
    public Map<String, Chat> chats = new HashMap<>();

    public Boolean transmitMessage(Message m) {

    }
}
