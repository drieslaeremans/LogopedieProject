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

public class Oefening2Activity extends AppCompatActivity {
    private Kind kind;
    private Woord woord;
    private Oefening oefening;
    SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        kind = (Kind) intent.getSerializableExtra("kind");
        woord = (Woord) intent.getSerializableExtra("woord");
        oefening = (Oefening) intent.getSerializableExtra("oefening");

        leesWoord();

        soundManager = new SoundManager(this);
        soundManager.Play( "woord_" + woord.getWoord());
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
    }

    public void onClickWoordSprekenOef2(View v) {
        if (!soundManager.isPlaying()) {
            soundManager.Play("woord_" + woord.getWoord().toLowerCase());
        }
    }

    public void volgendeOefening(View v)
    {
        if(!soundManager.isPlaying()) {
            oefening.setOefening2(true);

            Intent intent = new Intent(this, Oefening3Activity.class);
            intent.putExtra("woord", woord );
            intent.putExtra("kind", kind);
            intent.putExtra("oefening", oefening);

            startActivityForResult(intent, 1);
        }
    }

    @Override
    public void onBackPressed() {
        soundManager.resetQueue();
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == RESULT_CANCELED) {
                setResult(RESULT_CANCELED);
                finish();
            } else if (resultCode == RESULT_OK) {
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

}
