package dev.droppinganvil.v3.keychange;

import me.droppinganvil.core.mysql.annotations.MemoryOnly;

import java.io.Serializable;

public class ServerKey implements Serializable {

    public ServerKey(String primaryKey, String secondaryKey, String tempKey, String serverIP, Long valid, Boolean active) {
        this.primaryKey = primaryKey;
        this.secondaryKey = secondaryKey;
        this.tempKey = tempKey;
        this.serverIP = serverIP;
        this.valid = valid;
        this.active = active;
    }

    public ServerKey() {
    }

    public String primaryKey;
    public String secondaryKey;
    @MemoryOnly
    public String tempKey;
    public String serverIP;
    public Long valid;
    public Boolean active;
}
