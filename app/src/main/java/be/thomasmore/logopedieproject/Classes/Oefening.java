package be.thomasmore.logopedieproject.Classes;

import android.content.Context;

import java.io.Serializable;

import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;


public class Oefening implements Serializable {
    private long id;
    private boolean oefening1 = false;
    private boolean oefening2 = false;
    private boolean oefening3 = false;
    private boolean oefening4 = false;
    private boolean oefening5 = false;
    private boolean oefening6 = false;
    private Woord oefenwoord;
    private int groep;
    private Conditie conditie;

    public Oefening() {
    }

    public Oefening(long id, boolean oefening1, boolean oefening2, boolean oefening3, boolean oefening4, boolean oefening5, boolean oefening6, Woord oefenwoord, int groep) {
        this.id = id;
        this.oefening1 = oefening1;
        this.oefening2 = oefening2;
        this.oefening3 = oefening3;
        this.oefening4 = oefening4;
        this.oefening5 = oefening5;
        this.oefening6 = oefening6;
        this.oefenwoord = oefenwoord;
        this.groep = groep;

        bepaalConditie();
    }

    public Conditie bepaalConditie()
    {

        // Aan de hand van de geselecteerde groep de conditie bepalen
        return Woord.bepaalConditie(this.groep, this.oefenwoord);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isOefening1() {
        return oefening1;
    }

    public void setOefening1(boolean oefening1) {
        this.oefening1 = oefening1;
    }

    public boolean isOefening2() {
        return oefening2;
    }

    public void setOefening2(boolean oefening2) {
        this.oefening2 = oefening2;
    }

    public boolean isOefening3() {
        return oefening3;
    }

    public void setOefening3(boolean oefening3) {
        this.oefening3 = oefening3;
    }

    public boolean isOefening4() {
        return oefening4;
    }

    public void setOefening4(boolean oefening4) {
        this.oefening4 = oefening4;
    }

    public boolean isOefening5() {
        return oefening5;
    }

    public void setOefening5(boolean oefening5) {
        this.oefening5 = oefening5;
    }

    public boolean isOefening6() {
        return oefening6;
    }

    public void setOefening6(boolean oefening6) {
        this.oefening6 = oefening6;
    }

    public Woord getOefenwoord() {
        return oefenwoord;
    }

    public void setOefenwoord(Woord oefenwoord) {
        this.oefenwoord = oefenwoord;
        bepaalConditie();

    }



    public int getGroep() {
        return groep;
    }

    public void setGroep(int groep) {
        this.groep = groep;
        bepaalConditie();
    }


    public String getResult() {
        boolean[] results = {isOefening1(), isOefening2(), isOefening3(), isOefening4(), isOefening5(), isOefening6()};

        return ("" + getScore(results) + "/" + results.length);
    }

    public String toStringOefenwoord(Context context) {
        //DatabaseHelper db = new DatabaseHelper(context);
        return oefenwoord.getWoord();
    }

    private int getScore(boolean... results) {
        int count = 0;
        for (boolean result: results) {
            count += (result ? 1 : 0);
        }
        return count;
    }
}
