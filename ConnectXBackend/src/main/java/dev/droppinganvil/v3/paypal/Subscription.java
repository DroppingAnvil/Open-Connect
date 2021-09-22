package dev.droppinganvil.v3.paypal;

public interface Subscription {
    void end() throws Exception;

    void suspend() throws Exception;

    void reinstate() throws Exception;
}
