package be.thomasmore.logopedieproject.Classes;

import java.io.Serializable;

public class Woord implements Serializable {
    private long id;
    private String lidwoord;
    private String woord;
    private String definitie;
    private String juisteContext;
    private String fouteContext;
    private String lettergrepen;
    private String semantischWeb;
    private int conditie1;
    private int conditie2;
    private int conditie3;
    private boolean oefenwoord;

    public Woord() {

    }

    public Woord(long id, String lidwoord, String woord, String definitie, String juisteContext, String fouteContext,
                 String lettergrepen, String semantischWeb, int conditie1, int conditie2, int conditie3, boolean oefenwoord) {
        this.id = id;
        this.lidwoord = lidwoord;
        this.woord = woord;
        this.definitie = definitie;
        this.juisteContext = juisteContext;
        this.fouteContext = fouteContext;
        this.lettergrepen = lettergrepen;
        this.semantischWeb = semantischWeb;
        this.conditie1 = conditie1;
        this.conditie2 = conditie2;
        this.conditie3 = conditie3;
        this.oefenwoord = oefenwoord;
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

    public String getLidwoord() {
        return lidwoord;
    }

    public void setLidwoord(String lidwoord) {
        this.lidwoord = lidwoord;
    }

    public String getSemantischWeb() {
        return semantischWeb;
    }

    public void setSemantischWeb(String semantischWeb) {
        this.semantischWeb = semantischWeb;
    }

    public int getConditie1() {
        return conditie1;
    }

    public void setConditie1(int conditie1) {
        this.conditie1 = conditie1;
    }

    public int getConditie2() {
        return conditie2;
    }

    public void setConditie2(int conditie2) {
        this.conditie2 = conditie2;
    }

    public int getConditie3() {
        return conditie3;
    }

    public void setConditie3(int conditie3) {
        this.conditie3 = conditie3;
    }

    public boolean isOefenwoord() {
        return oefenwoord;
    }

    public void setOefenwoord(boolean oefenwoord) {
        this.oefenwoord = oefenwoord;
    }

    @Override
    public String toString() {
        return woord;
    }

    public String toStringVolledigWoord() {
        return lidwoord + " " + woord;
    }


    public static Conditie bepaalConditie(int groep, Woord woord)
    {
        Conditie conditie = Conditie.ONGELDIGE_CONDITIE;

        if(woord.getConditie1() == groep)
            conditie = Conditie.TRAINEN_NIET_FONOLOGISCH_VERKENNEN;
        else if(woord.getConditie2() == groep)
            conditie = Conditie.TRAINEN_FONOLOGISCH_VERKENNEN_ZOEMEND;
        else if(woord.getConditie3() == groep)
            conditie = Conditie.TRAINEN_FONOLOGISCH_VERKENNEN_KLANKGROEPEN;



        System.out.println("CONDITIE: " + woord.getWoord() + " in groep "+groep+" is: " + conditie.ordinal());
        return conditie;
    }

}
