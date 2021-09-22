package dev.droppinganvil.v3.network.nodemesh;

import dev.droppinganvil.v3.Configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NetworkContainer implements Serializable {
    public List<String> fingerprints = new ArrayList<>();
    public void fingerprint() {
        generateFingerprint(null);
    }
    public void fingerprint(String s) {
        generateFingerprint(s);
    }
    private String generateFingerprint(String s) {
        return Configuration.serverID + " | " + s + s==null?"":" | "+"Formatted Time";
    }
}
