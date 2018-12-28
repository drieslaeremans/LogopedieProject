package be.thomasmore.logopedieproject.Classes;

import android.content.Context;

import java.io.Serializable;

import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;

public class Oefening implements Serializable {
    private long id;
    private boolean oefening1;
    private boolean oefening2;
    private boolean oefening3;
    private boolean oefening4;
    private boolean oefening5;
    private boolean oefening6;
    private long oefenwoordId;

    public Oefening() {
    }

    public Oefening(long id, boolean oefening1, boolean oefening2, boolean oefening3, boolean oefening4, boolean oefening5, boolean oefening6, long oefenwoordId) {
        this.id = id;
        this.oefening1 = oefening1;
        this.oefening2 = oefening2;
        this.oefening3 = oefening3;
        this.oefening4 = oefening4;
        this.oefening5 = oefening5;
        this.oefening6 = oefening6;
        this.oefenwoordId = oefenwoordId;
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

    public long getOefenwoordId() {
        return oefenwoordId;
    }

    public void setOefenwoordId(long oefenwoordId) {
        this.oefenwoordId = oefenwoordId;
    }

    public String getResult() {
        boolean[] results = {isOefening1(), isOefening2(), isOefening3(), isOefening4(), isOefening5(), isOefening6()};

        return ("" + getResult(results) + "/" + results.length);
    }

    public String toStringOefenwoord(Context context) {
        DatabaseHelper db = new DatabaseHelper(context);
        return db.getWoord(getOefenwoordId()).getWoord();
    }

    private int getResult(boolean... results) {
        int count = 0;
        for (boolean result: results) {
            count += (result ? 1 : 0);
        }
        return count;
    }
}
