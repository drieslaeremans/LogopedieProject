package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Meting;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.Classes.WoordInMeting;
import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;

public class MetingActivity extends AppCompatActivity {

    private Kind kind;
    private DatabaseHelper db;
    private List<Woord> woorden;
    private int woord = 0;
    private List<WoordInMeting> gemetenWoorden = new ArrayList<WoordInMeting>();
    private Meting meting;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);
        soundManager = new SoundManager(this);
        meting = new Meting(0);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);

        haalWoordenOp();
        leesKind(id);

        startMeting();
    }

    private void haalWoordenOp() {
        this.woorden = this.db.getWoorden();
    }

    private void leesKind(long id)
    {
        this.kind = this.db.getKind(id);
    }

    private void startMeting() {
        Collections.shuffle(woorden);
        volgendeMeting();
    }

    private void volgendeMeting() {
        Woord woord = woorden.get(this.woord);
        TextView textViewWoord = (TextView) findViewById(R.id.woord);
        textViewWoord.setText(woord.getWoord());

        List<Integer> afbeeldingen = new ArrayList<Integer>();
        afbeeldingen.add(this.woord);

        for (int i = 0; i <= 2; i++) {
            Random r = new Random();
            int a = r.nextInt(10);

            while (afbeeldingen.contains(a)) {
                a = r.nextInt(10);
            }
            afbeeldingen.add(a);
        }

        Collections.shuffle(afbeeldingen);
        for(int i = 0; i <= 3; i++) {
            ImageView image = (ImageView) findViewById(
                    getResources().getIdentifier(("afbeelding" + i), "id", getPackageName())
            );
            image.setTag( woorden.get(afbeeldingen.get(i)).getId());
            image.setImageResource(
                    getResources().getIdentifier("woord_" + woorden.get(afbeeldingen.get(i)).getWoord().toLowerCase(), "drawable", getPackageName())
            );
        }

        spreek("woord_" + woorden.get(this.woord).getWoord().toLowerCase());
    }

    public void onClickAfbeelding(View v) {
        WoordInMeting woordInMeting = new WoordInMeting(
                1,
                this.woorden.get(this.woord).getId(),
                1,
                this.woorden.get(this.woord).getId() == (Long) v.getTag()
        );

        gemetenWoorden.add(woordInMeting);

        System.out.println("Antwoord is " + woordInMeting.isJuistOfFout());

        this.woord++;
        if(this.woord > woorden.size() -1) {
            eindeMeting();
        } else {
            volgendeMeting();
        }
    }

    private void eindeMeting() {

        Intent intent = new Intent(this, NaMetingActivity.class);

        intent.putExtra("list", (Serializable) gemetenWoorden);
        intent.putExtra("meting", meting);

        startActivityForResult(intent, 1);

    }

    public void onClickWoordSpreken(View v) {
        spreek("woord_" + woorden.get(woord).getWoord().toLowerCase());
    }

    private void spreek(String bestand) {
        soundManager.Play(bestand);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK, intent);
                finish();
            } else {
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }

}
