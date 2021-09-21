package dev.droppinganvil.v2.control.paypal;

public interface Subscription {
    void end() throws Exception;

    void suspend() throws Exception;

    void reinstate() throws Exception;
}
