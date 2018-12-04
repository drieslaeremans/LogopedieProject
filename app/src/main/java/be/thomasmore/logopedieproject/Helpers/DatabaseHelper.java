package be.thomasmore.logopedieproject.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject.Classes.Kind;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 7;

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


        insertKinderen(db);
        insertWoorden(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS woord");
        db.execSQL("DROP TABLE IF EXISTS kind");
        db.execSQL("DROP TABLE IF EXISTS meting");
        db.execSQL("DROP TABLE IF EXISTS woordInMeting");

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
                "'Zwaan', 'De vijver, vleugels, wit, het boek', 2, 3, 1, 0 );");
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
}