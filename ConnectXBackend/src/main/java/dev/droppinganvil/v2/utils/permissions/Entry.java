package dev.droppinganvil.v2.utils.permissions;

public interface Entry {
    String getName();
    boolean getAllow();
    void setAllow(boolean b);
    Integer getWeight();
}
