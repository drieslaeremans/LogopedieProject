package be.thomasmore.logopedieproject.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Meting;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.Sessie;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.Classes.WoordInMeting;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 10;

    private static final String DATABASE_NAME = "logopedie";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_WOORD = "CREATE TABLE woord (" +
                "id INTEGER PRIMARY KEY," +
                "lidwoord TEXT," +
                "woord TEXT," +
                "definitie TEXT," +
                "juisteContext TEXT," +
                "fouteContext TEXT," +
                "lettergrepen TEXT," +
                "semantischWeb TEXT," +
                "conditie1 INTEGER," +
                "conditie2 INTEGER," +
                "conditie3 INTEGER," +
                "oefenwoord BOOLEAN)";
        db.execSQL(CREATE_TABLE_WOORD);

        String CREATE_TABLE_KIND = "CREATE TABLE kind (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "voornaam TEXT," +
                "achternaam TEXT)";
        db.execSQL(CREATE_TABLE_KIND);

        String CREATE_TABLE_METING = "CREATE TABLE meting (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "kindId INTEGER," +
                "type TEXT, " +
                "FOREIGN KEY (kindId) REFERENCES kind(id) )";
        db.execSQL(CREATE_TABLE_METING);

        String CREATE_TABLE_WOORDINMETING = "CREATE TABLE woordInMeting (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woordId INTEGER," +
                "metingId INTEGER," +
                "juistOfFout BOOLEAN," +
                "FOREIGN KEY (metingId) REFERENCES meting(id)," +
                "FOREIGN KEY (woordId) REFERENCES woord(id) )";
        db.execSQL(CREATE_TABLE_WOORDINMETING);

        String CREATE_TABLE_OEFENING = "CREATE TABLE oefening (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "oefening1 BOOLEAN, " +
                "oefening2 BOOLEAN, " +
                "oefening3 BOOLEAN, " +
                "oefening4 BOOLEAN, " +
                "oefening5 BOOLEAN, " +
                "oefening6 BOOLEAN, " +
                "oefenwoordId INTEGER, " +
                "groep INTEGER, " +
                "FOREIGN KEY (oefenwoordId) REFERENCES woord(id) )";
        db.execSQL(CREATE_TABLE_OEFENING);

        String CREATE_TABLE_SESSIE = "CREATE TABLE sessie (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "datum TEXT, " +
                "kindId INTEGER, " +
                "voormetingId INTEGER, " +
                "nametingId INTEGER, " +
                "oefeningId INTEGER," +
                "FOREIGN KEY (kindId) REFERENCES kind(id)," +
                "FOREIGN KEY (voormetingId) REFERENCES meting(id)," +
                "FOREIGN KEY (nametingId) REFERENCES meting(id)," +
                "FOREIGN KEY (oefeningId) REFERENCES oefening(id) )";
        db.execSQL(CREATE_TABLE_SESSIE);


        insertKinderen(db);
        insertWoorden(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS woord");
        db.execSQL("DROP TABLE IF EXISTS kind");
        db.execSQL("DROP TABLE IF EXISTS meting");
        db.execSQL("DROP TABLE IF EXISTS woordInMeting");
        db.execSQL("DROP TABLE IF EXISTS oefening");
        db.execSQL("DROP TABLE IF EXISTS sessie");
        onCreate(db);
    }

    private void insertWoorden(SQLiteDatabase db) {
        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (1, 'De', 'Duikbril', " +
                "'Een duikbril is een bril voor onder water. Daarmee kun je onder water je ogen open houden. ', " +
                "'Met zijn duikbril kan de jongen de vissen onder water goed bekijken.', " +
                "'Met een duikbril kan ik schrijven op papier.', " +
                "'Duik-bril', 'Ogen,zwemmen,in de zee,schrijven', null, null, null, 1 );");

        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (2, 'Het', 'Klimtouw', " +
                "'Een klimtouw is een touw waarin je omhoog kunt klimmen. ', " +
                "'In de turnzaal klim ik omhoog in het klimtouw. ', " +
                "'Ik wacht op de bus in het klimtouw.', " +
                "'Klim-touw', 'Klimmen,sterk,de turnzaal,het zwembad', 1, 2, 3, 0 );");

        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (3, 'Het', 'Kroos', " +
                "'Kroos bestaat uit kleine, groene plantjes die op het water groeien. Je ziet het bijvoorbeeld in een sloot. ', " +
                "'De vijver is groen door het kroos. ', " +
                "'Oma en het kroos zitten in de auto.', " +
                "'Kroos', 'Groen,in de vijver,de eend,de lamp', 1, 2, 3, 0 );");
        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (4, 'Het', 'Riet', " +
                "'Riet lijkt op hoog gras. Het heeft lange stengels en groeit langs het water. ', " +
                "'De eenden zitten bij het water tussen het riet', " +
                "'Ik ga naar buiten met mijn jas en het riet aan.', " +
                "'Riet', 'De vijver,de eend,het bos,de bril', 1, 2, 3, 0 );");
        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (5, 'De', 'Val', " +
                "'Als je een val maakt, val je op de grond.', " +
                "'Wat was dat een pijnlijke val!', " +
                "'Jan zit op de val aan tafel.', " +
                "'Val', 'De pijn,naar voor,de pleister,de appel', 3, 1, 2, 0 );");
        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (6, 'Het', 'Kompas', " +
                "'Met een kompas weet je waar je naartoe moet. De naald van het kompas geeft het noorden aan.   ', " +
                "'Omdat papa niet weet waar we naartoe moeten lopen, kijkt hij op zijn kompas. ', " +
                "'Mama belt met het kompas naar papa.', " +
                "'Kom-pas', 'Wandelen,de rugzak,de landkaart,het bad', 3, 1, 2, 0 );");
        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (7, null, 'Steil', " +
                "'Een steile berg gaat heel schuin omhoog of omlaag.', " +
                "'Jan loopt de steile berg omhoog.', " +
                "'Papa leest een steil verhaaltje voor. ', " +
                "'Steil', 'De berg,beklimmen,de trap,de bloem', 3, 1, 2, 0 );");
        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (8, 'De', 'Zwaan', " +
                "'Een zwaan is een grote vogel met een lange, kromme hals. Meestal zijn zwanen wit, maar soms zwart. ', " +
                "'In de vijver in het park zwemt een witte zwaan.', " +
                "'De zwaan fietst in het park. ', " +
                "'Zwaan', 'De vijver,vleugels,wit,het boek', 2, 3, 1, 0 );");
        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (9, 'Het', 'Kamp', " +
                "'Een kamp is een plaats om buiten te wonen en te slapen, bijvoorbeeld in tenten. ', " +
                "'De kinderen zitten te eten tussen de tenten van het kamp.', " +
                "'Jonas wast zich met het kamp.', " +
                "'', 'De tent,kampvuur,in de slaapzak,de deur', 2, 3, 1, 0 );");
        db.execSQL("INSERT INTO woord (id, lidwoord, woord, definitie, juisteContext, fouteContext, lettergrepen, semantischWeb, conditie1, conditie2, conditie3, oefenwoord)" +
                "VALUES (10, 'De', 'Zaklamp', " +
                "'Een zaklamp is een kleine lamp die je overal mee naartoe kunt nemen. ', " +
                "'De jongen schijnt met de zaklamp in de donkere grot.  ', " +
                "'Jef opent de deur met de zaklamp.', " +
                "'Zak-lamp', 'Het licht,de batterij,in het donker,het paard', 2, 3, 1, 0 );");
    }

    private void insertKinderen(SQLiteDatabase db) {
        db.execSQL("INSERT INTO kind(voornaam, achternaam) VALUES ('Tom', 'Vdr')");
        db.execSQL("INSERT INTO kind(voornaam, achternaam) VALUES ('Dries', 'L')");
    }

    public Woord getWoordByNaam(String woord)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "woord",
                new String[] {
                        "id", "lidwoord", "woord",
                        "definitie", "juisteContext", "fouteContext",
                        "lettergrepen", "semantischWeb",
                        "conditie1", "conditie2", "conditie3",
                        "oefenwoord"
                },
                "woord = ?",
                new String[] { String.valueOf(woord) }
                , null, null, null);


        Woord w = new Woord();
        if (c.moveToFirst()) {
            w = new Woord(
                    c.getLong(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4),
                    c.getString(5),
                    c.getString(6),
                    c.getString(7),
                    c.getInt(8),
                    c.getInt(9),
                    c.getInt(10),
                    c.getInt(11) == 1
            );
        }
        c.close();
        db.close();
        return w;
    }

    public Sessie getSessie(long id)
    {
        /*
            private long id;
            private String datum;
            private long kindId;
            private long voormetingId;
            private long nametingId;
            private long oefeningId;
        */

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "sessie",
                new String[] {"id", "datum", "kindId", "voormetingId", "nametingId", "oefeningId"  },
                "id = ?",
                new String[] { String.valueOf(id) },
                null, null, null, null);


        Sessie sessie = new Sessie();
        if (c.moveToFirst()) {
            sessie = new Sessie(c.getLong(0), c.getString(1), c.getLong(2), c.getLong(3), c.getLong(4), c.getLong(5));
        }
        c.close();
        db.close();
        return sessie;
    }

    public Kind getKind(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "kind",
                new String[] {"id", "voornaam", "achternaam"},
                "id = ?",
                new String[] { String.valueOf(id) },
                null, null, null, null);


        Kind kind = new Kind();
        if (c.moveToFirst()) {
            kind = new Kind(c.getLong(0), c.getString(1), c.getString(2));
        }
        c.close();
        db.close();
        return kind;
    }

    public List<Kind> getKinderen() {
        List<Kind> lijst = new ArrayList<Kind>();
        String query = "SELECT * from kind ORDER BY voornaam";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Kind kind = new Kind(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
                lijst.add(kind);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lijst;
    }

    public long createKind(Kind kind)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("voornaam", kind.getVoornaam());
        values.put("achternaam", kind.getAchternaam());

        long id = db.insert("kind", null, values);

        db.close();
        return id;
    }

    public int updateKind(Kind kind)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("voornaam", kind.getVoornaam());
        values.put("achternaam", kind.getAchternaam());


        int numrows = db.update("kind", values, "id = ?", new String[]{ String.valueOf(kind.getId())});

        db.close();

        return numrows;
    }

    public List<Woord> getWoorden() {
        List<Woord> lijst = new ArrayList<Woord>();
        String query = "SELECT * from woord";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Woord woord = new Woord(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(8),
                        cursor.getInt(9),
                        cursor.getInt(10),
                        cursor.getInt(11) == 1
                        );
                lijst.add(woord);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lijst;
    }

    public List<Woord> getDoelwoorden() {
        List<Woord> lijst = new ArrayList<Woord>();
        String query = "SELECT * from woord WHERE oefenwoord = 0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Woord woord = new Woord(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(8),
                        cursor.getInt(9),
                        cursor.getInt(10),
                        cursor.getInt(11) == 1
                );
                lijst.add(woord);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lijst;
    }


    public Woord getWoord(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "woord",
                new String[] {
                        "id", "lidwoord", "woord",
                        "definitie", "juisteContext", "fouteContext",
                        "lettergrepen", "semantischWeb",
                        "conditie1", "conditie2", "conditie3",
                        "oefenwoord"
                },
                "id = ?",
                new String[] { String.valueOf(id) },
                null, null, null, null);


        Woord woord = new Woord();
        if (c.moveToFirst()) {
            woord = new Woord(
                    c.getLong(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4),
                    c.getString(5),
                    c.getString(6),
                    c.getString(7),
                    c.getInt(8),
                    c.getInt(9),
                    c.getInt(10),
                    c.getInt(11) == 1
            );
        }
        c.close();
        db.close();
        return woord;
    }



    public Woord getOefenwoord() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "woord",
                new String[] {
                        "id", "lidwoord", "woord",
                        "definitie", "juisteContext", "fouteContext",
                        "lettergrepen", "semantischWeb",
                        "conditie1", "conditie2", "conditie3",
                        "oefenwoord"
                },
                "oefenwoord = ?",
                new String[] { String.valueOf(1) },
                null, null, null, null);


        Woord woord = new Woord();
        if (c.moveToFirst()) {
            woord = new Woord(
                    c.getLong(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4),
                    c.getString(5),
                    c.getString(6),
                    c.getString(7),
                    c.getInt(8),
                    c.getInt(9),
                    c.getInt(10),
                    c.getInt(11) == 1
            );
        }
        c.close();
        db.close();
        return woord;
    }

    public long sessieOpslagen(Sessie sessie, Meting voormeting, List<WoordInMeting> voormetingWoorden, Meting nameting,  List<WoordInMeting> nametingWoorden, Oefening oefening) {
        //Voormeting opslagen
        sessie.setVoormetingId(this.createMeting(voormeting));
        for (WoordInMeting gemetenWoord : voormetingWoorden) {
            gemetenWoord.setMetingId(sessie.getVoormetingId());
        }
        this.createWoordenInMeting(voormetingWoorden);

        //Nameting opslagen
        sessie.setNametingId(this.createMeting(nameting));
        for (WoordInMeting gemetenWoord : nametingWoorden) {
            gemetenWoord.setMetingId(sessie.getNametingId());
        }
        this.createWoordenInMeting(nametingWoorden);

        //Oefening opslagen
        sessie.setOefeningId(this.createOefening(oefening));

        //Sessie opslagen
        return this.createSessie(sessie);
    }

    public long createMeting(Meting meting) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        //values.put("id", null);
        long id = db.insert("meting", "id", values);

        db.close();
        return id;
    }

    public void createWoordenInMeting(List<WoordInMeting> gemetenWoorden) {
        SQLiteDatabase db = this.getReadableDatabase();

        for(WoordInMeting gemetenWoord : gemetenWoorden) {
            ContentValues values = new ContentValues();
            values.put("woordId", gemetenWoord.getWoordId());
            values.put("metingId", gemetenWoord.getMetingId());
            values.put("juistOfFout", gemetenWoord.isJuistOfFout());

            db.insert("woordInMeting", null, values);
        }

        db.close();
    }

    private long createOefening(Oefening oefening) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("oefening1", oefening.isOefening1());
        values.put("oefening2", oefening.isOefening2());
        values.put("oefening3", oefening.isOefening3());
        values.put("oefening4", oefening.isOefening4());
        values.put("oefening5", oefening.isOefening5());
        values.put("oefening6", oefening.isOefening6());
        values.put("oefenwoordId", oefening.getOefenwoord().getId());
        values.put("groep", oefening.getGroep());

        long id = db.insert("oefening", null, values);
        db.close();

        return id;
    }

    private long createSessie(Sessie sessie) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put("datum", sessie.getDatum());
        values.put("kindId", sessie.getKindId());
        values.put("voormetingId", sessie.getVoormetingId());
        values.put("nametingId", sessie.getNametingId());
        values.put("oefeningId", sessie.getOefeningId());

        long id = db.insert("sessie", null, values);
        db.close();

        return id;
    }
}
