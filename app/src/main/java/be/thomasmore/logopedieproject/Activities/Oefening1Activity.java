package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.Classes.WoordInMeting;
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

        soundManager.Play("woord_" + woord.getWoord().toLowerCase());
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

    public void volgendeOefening(View v)
    {
        Intent intent = new Intent(this, Oefening2Activity.class);
        intent.putExtra("woord", woord);
        intent.putExtra("kind", kind);
        oefening.setOefening1(true);
        intent.putExtra("oefening", oefening);

        startActivityForResult(intent, 1);
//        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        soundManager.ResetQueue();
        soundManager.stopPlaying();
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
                Intent _intent = new Intent();
                setResult(RESULT_OK, _intent);
                finish();
            }
        }
    }
}
