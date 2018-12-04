package be.thomasmore.logopedieproject;

public class Kind {
    private long id;
    private String voornaam;

    public Kind() {
    }

    public Kind(long id, String voornaam) {
        this.id = id;
        this.voornaam = voornaam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    @Override
    public String toString() {
        return voornaam.trim();
    }
}
