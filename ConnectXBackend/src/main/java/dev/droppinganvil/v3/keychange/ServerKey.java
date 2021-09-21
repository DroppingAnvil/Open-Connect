package dev.droppinganvil.v3.keychange;

import me.droppinganvil.core.mysql.annotations.MemoryOnly;

public class ServerKey {
    //TODO Crypt

    public ServerKey(String primaryKey, String secondaryKey, String tempKey, String requestKey, String serverIP, Long valid, Boolean active) {
        this.primaryKey = primaryKey;
        this.secondaryKey = secondaryKey;
        this.tempKey = tempKey;
        this.requestKey = requestKey;
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
    @MemoryOnly
    public String requestKey;
    @MemoryOnly
    public String serverIP;
    public Long valid;
    public Boolean active;
}
