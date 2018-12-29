package be.thomasmore.logopedieproject.Classes;

import java.io.Serializable;

public class Meting implements Serializable {
    private long id;

    public Meting() {
    }

    public Meting(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
