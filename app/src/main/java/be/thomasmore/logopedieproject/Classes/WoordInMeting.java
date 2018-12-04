package be.thomasmore.logopedieproject.Classes;

public class WoordInMeting {
    private long id;
    private long woordId;
    private long metingId;
    private boolean juistOfFout;

    public WoordInMeting(long id, long woordId, long metingId, boolean juistOfFout) {
        this.id = id;
        this.woordId = woordId;
        this.metingId = metingId;
        this.juistOfFout = juistOfFout;
    }

    public WoordInMeting() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWoordId() {
        return woordId;
    }

    public void setWoordId(long woordId) {
        this.woordId = woordId;
    }

    public long getMetingId() {
        return metingId;
    }

    public void setMetingId(long metingId) {
        this.metingId = metingId;
    }

    public boolean isJuistOfFout() {
        return juistOfFout;
    }

    public void setJuistOfFout(boolean juistOfFout) {
        this.juistOfFout = juistOfFout;
    }
}
