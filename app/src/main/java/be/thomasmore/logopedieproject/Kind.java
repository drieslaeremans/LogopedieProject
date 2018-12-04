package be.thomasmore.logopedieproject;

public class Kind {
    private long id;
    private String voornaam;
    private String achternaam;

    public Kind() {
    }


    public Kind(long id, String voornaam, String achternaam) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVoornaam() {
        if(voornaam == null)
            return "Geen naam";

        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }


    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }


    @Override
    public String toString() {
        return getVoornaam().trim();
    }
}
