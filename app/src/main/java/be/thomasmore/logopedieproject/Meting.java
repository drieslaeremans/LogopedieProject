package be.thomasmore.logopedieproject;

public class Meting {
    private long id;
    private String type;
    private long kindId;

    public Meting() {
    }

    public Meting(long id, String type, long kindId) {
        this.id = id;
        this.type = type;
        this.kindId = kindId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getKindId() {
        return kindId;
    }

    public void setKindId(long kindId) {
        this.kindId = kindId;
    }
}
