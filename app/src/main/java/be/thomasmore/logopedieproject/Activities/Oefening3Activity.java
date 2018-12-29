package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.R;

public class Oefening3Activity extends AppCompatActivity {

    private Kind kind;
    private Woord woord;
    private Oefening oefening;
    SoundManager soundManager;

    ImageButton duimPositief;
    ImageButton duimNegatief;

    private boolean contextType; // true: juisteContext, false: fouteContext
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        kind = (Kind) intent.getSerializableExtra("kind");
        woord = (Woord) intent.getSerializableExtra("woord");
        oefening = (Oefening) intent.getSerializableExtra("oefening");

        soundManager = new SoundManager(this);

        Random r = new Random();
        contextType = r.nextBoolean();

        duimPositief = (ImageButton) findViewById(R.id.duimPositief) ;
        duimNegatief = (ImageButton) findViewById(R.id.duimNegatief) ;


        leesWoord();
        speelIntro();
    }

    private void leesWoord()
    {
        // Doelwoord instellen
        TextView doelwoord = (TextView) findViewById(R.id.woord);


        if(contextType)
            doelwoord.setText( woord.getJuisteContext() );
        else
            doelwoord.setText( woord.getFouteContext() );


        ImageView image = (ImageView) findViewById(R.id.afbeelding);

        image.setImageResource(
                getResources().getIdentifier("woord_" + woord.getWoord().toLowerCase(), "drawable", getPackageName())
        );
    }

    public void speelGeluid()
    {
        soundManager.ResetQueue();

        if(contextType)
            soundManager.AddQueue( "juistecontext_" + woord.getWoord());
        else
            soundManager.AddQueue( "foutecontext_" + woord.getWoord());



        soundManager.PlayQueue();
    }


    public void speelIntro()
    {
        soundManager.ResetQueue();

        soundManager.AddQueue( "zin_ikzou");
        soundManager.AddQueue( "woord_" + woord.getWoord());
        soundManager.AddQueue( "zin_graagineenzinnetje");

        if(contextType)
            soundManager.AddQueue( "juistecontext_" + woord.getWoord());
        else
            soundManager.AddQueue( "foutecontext_" + woord.getWoord());

        soundManager.PlayQueue();
    }

    public void onClickWoordSpreken(View v)
    {
        if(soundManager != null) {
            speelGeluid();
        }
    }

    public void volgendeOefening()
    {
        soundManager.ResetQueue();
        soundManager.stopPlaying();

        Intent intent = new Intent(this, Oefening4Activity.class);
        intent.putExtra("woord", woord );
        intent.putExtra("kind", kind);
        intent.putExtra("oefening", oefening);

        startActivityForResult(intent, 1);
    }

    public void checkAntwoord( boolean invoer )
    {
        soundManager.ResetQueue();

        if(invoer == contextType)
        {
            soundManager.AddQueue( "zin_goedzo");
            soundManager.PlayQueue();

            duimNegatief.setEnabled(false);
            duimNegatief.setClickable(false);

            duimPositief.setEnabled(false);
            duimPositief.setClickable(false);


            if(contextType ==  true)
                duimNegatief.setVisibility(View.INVISIBLE);
            else
                duimPositief.setVisibility(View.INVISIBLE);

            oefening.setOefening3(true);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    volgendeOefening();
                }
            }, 2000);

        } else
        {
            oefening.setOefening3(false);
            soundManager.AddQueue( "zin_oepsdatwasnietjuist");
            soundManager.PlayQueue();
        }

        System.out.println("Oefening3: checkAntwoord");

    }


    public void clickDuimNegatief(View v)
    {
        System.out.println("Oefening3: duim negatief aangeduid");
        checkAntwoord(false);
    }

    public void clickDuimPositief(View v)
    {
        System.out.println("Oefening3: duim positief aangeduid");
        checkAntwoord(true);
    }

    @Override
    public void onBackPressed() {
        soundManager.ResetQueue();
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
