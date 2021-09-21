package dev.droppinganvil.v2;

public class Notification {
    public String title;
    public String body;
    public boolean dismissed;
    public boolean persist;

    public Notification(String title, String body, boolean persist) {
        this.title = title;
        this.body = body;
        dismissed= false;
        this.persist = persist;
    }
}
