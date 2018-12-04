package be.thomasmore.logopedieproject;

public class Woord {
    private long id;
    private String lidwoord;
    private String woord;
    private String definitie;
    private String juisteContext;
    private String fouteContext;
    private String lettergrepen;

    public Woord() {
    }

    public Woord(long id, String woord, String definitie, String juisteContext, String fouteContext, String lettergrepen) {
        this.id = id;
        this.woord = woord;
        this.definitie = definitie;
        this.juisteContext = juisteContext;
        this.fouteContext = fouteContext;
        this.lettergrepen = lettergrepen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWoord() {
        return woord;
    }

    public void setWoord(String woord) {
        this.woord = woord;
    }

    public String getDefinitie() {
        return definitie;
    }

    public void setDefinitie(String definitie) {
        this.definitie = definitie;
    }

    public String getJuisteContext() {
        return juisteContext;
    }

    public void setJuisteContext(String juisteContext) {
        this.juisteContext = juisteContext;
    }

    public String getFouteContext() {
        return fouteContext;
    }

    public void setFouteContext(String fouteContext) {
        this.fouteContext = fouteContext;
    }

    public String getLettergrepen() {
        return lettergrepen;
    }

    public void setLettergrepen(String lettergrepen) {
        this.lettergrepen = lettergrepen;
    }

    @Override
    public String toString() {
        return woord;
    }

    public String toStringVolledigWoord() {
        return lidwoord + " " + woord;
    }
}
