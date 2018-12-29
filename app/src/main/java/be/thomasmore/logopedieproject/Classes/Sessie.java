package be.thomasmore.logopedieproject.Classes;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sessie implements Serializable {
    private long id;
    private String datum;
    private long kindId;
    private long voormetingId;
    private long nametingId;
    private long oefeningId;

    public Sessie() {
    }

    public Sessie(long id, String datum, long kindId, long voormetingId, long nametingId, long oefeningId) {
        this.id = id;
        this.datum = datum;
        this.kindId = kindId;
        this.voormetingId = voormetingId;
        this.nametingId = nametingId;
        this.oefeningId = oefeningId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
        this.datum = getCurrentDDMMYYYYDatum();
    }

    public long getKindId() {
        return kindId;
    }

    public void setKindId(long kindId) {
        this.kindId = kindId;
    }

    public long getVoormetingId() {
        return voormetingId;
    }

    public void setVoormetingId(long voormetingId) {
        this.voormetingId = voormetingId;
    }

    public long getNametingId() {
        return nametingId;
    }

    public void setNametingId(long nametingId) {
        this.nametingId = nametingId;
    }

    public long getOefeningId() {
        return oefeningId;
    }

    public void setOefeningId(long oefeningId) {
        this.oefeningId = oefeningId;
    }

    @Override
    public String toString() {
        return getDatum();
    }

    private String getCurrentDDMMYYYYDatum() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(new Date());
    }
}
