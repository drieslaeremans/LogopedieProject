package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.R;

public class Oefening1Activity extends AppCompatActivity {
    private Kind kind;
    private Woord woord;
    private Oefening oefening;
    SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        kind = (Kind) intent.getSerializableExtra("kind");
        woord = (Woord) intent.getSerializableExtra("woord");
        oefening = (Oefening) intent.getSerializableExtra("oefening");

        leesWoord();

        soundManager = new SoundManager(this);
        //spreek("woord_" + woord.getWoord().toLowerCase());
        spreek("definitie_" + woord.getWoord().toLowerCase());

        soundManager.playQueue();
    }

    private void leesWoord()
    {
        // Doelwoord instellen
        TextView doelwoord = (TextView) findViewById(R.id.woord);
        doelwoord.setText( woord.getWoord() );

        ImageView image = (ImageView) findViewById(R.id.afbeelding);
        image.setImageResource(
                getResources().getIdentifier("woord_" + woord.getWoord().toLowerCase(), "drawable", getPackageName())
        );
        image.setContentDescription(woord.getLidwoord() + " " + woord.getWoord());
    }

    public void onClickDefinitieSpreken(View v) {
        soundManager.resetQueue();
        spreek("definitie_" + woord.getWoord().toLowerCase());
        soundManager.playQueue();
    }

    private void spreek(String bestand) {
        soundManager.addQueue(bestand);
    }

    public void volgendeOefening(View v) {
        soundManager.resetQueue();
        soundManager.stopPlaying();
        oefening.setOefening1(true);

        Intent intent = new Intent(this, Oefening2Activity.class);
        intent.putExtra("woord", woord);
        intent.putExtra("kind", kind);
        intent.putExtra("oefening", oefening);

        startActivityForResult(intent, 1);

    }

    @Override
    public void onBackPressed() {
        soundManager.resetQueue();
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        soundManager.resetQueue();
        if (requestCode == 1) {
            if (resultCode == RESULT_CANCELED) {
                setResult(RESULT_CANCELED);
                finish();
            } else if (resultCode == RESULT_OK) {
                Intent _intent = new Intent();
                setResult(RESULT_OK, _intent);
                finish();
            }
        }
    }
}
