package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.R;

import static java.lang.Thread.sleep;

public class Oefening4Activity extends AppCompatActivity implements ImageView.OnClickListener {
    private Kind kind;
    private Woord woord;
    private Oefening oefening;
    private List<String> semantischeWoorden;
    private String fouteWoord;
    private List<String> antwoorden = new ArrayList<String>();
    private Handler handler = new Handler(); // Timer
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        woord = (Woord) intent.getSerializableExtra("woord");
        kind = (Kind) intent.getSerializableExtra("kind");
        oefening = (Oefening) intent.getSerializableExtra("oefening");

        soundManager = new SoundManager(this);

        semantischeWoorden = Arrays.asList(woord.getSemantischWeb().split(","));
        fouteWoord = semantischeWoorden.get( semantischeWoorden.size()-1 );
        Collections.shuffle(semantischeWoorden);

        System.out.println("Oefening4: foute woord:  " + fouteWoord);

        leesFotos();

    }

    private void leesFotos()
    {
        TextView doelwoord = (TextView) findViewById(R.id.woord);
        doelwoord.setText(woord.getWoord());

        ImageButton image = (ImageButton) findViewById(R.id.afbeelding);
        image.setImageResource(
                getResources().getIdentifier("woord_" + woord.getWoord().toLowerCase(), "drawable", getPackageName())
        );

        for (int i = 0; i < 4; i++)
        {
            image = (ImageButton) findViewById(
                    getResources().getIdentifier(("woord" + i), "id", getPackageName())
            );

            String woord = "oef4_" + (semantischeWoorden.get(i)).replace(" ", "_").toLowerCase();

            System.out.println("Woord: " + woord);
            image.setTag(semantischeWoorden.get(i).toLowerCase());

            image.setOnClickListener(this);
            image.setPadding(15,15,15,15);
            image.setImageResource(
                    getResources().getIdentifier(woord, "drawable", getPackageName())
            );
        }
    }

    @Override
    public void onClick(View v)
    {
        ImageButton button = (ImageButton) v;


        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setStroke(6, Color.BLUE);
        drawable.setColor(Color.WHITE);
        button.setBackgroundDrawable(drawable);

        String woord = (String) button.getTag();

        System.out.println("Oefening4: geselecteerd: " + woord);

        if(antwoorden.indexOf(woord) < 0)
            antwoorden.add(woord);

        if(antwoorden.size() == 3)
            checkAntwoorden();
    }

    private void checkAntwoorden()
    {
        boolean correct = true;

        for(int i = 0; i < antwoorden.size(); i++)
        {
            if(antwoorden.get(i).equals(fouteWoord))
                correct =false;
        }

        if(correct)
            onJuisteAntwoorden();
        else
            onFouteAntwoorden();
    }

    private void onJuisteAntwoorden()
    {
        soundManager.resetQueue();

        Intent intent = new Intent(this, Oefening5Activity.class);

        intent.putExtra("kind", kind);
        intent.putExtra("woord", woord);
        oefening.setOefening4(true);
        intent.putExtra("oefening", oefening);

        startActivityForResult(intent, 1);
    }

    private void onFouteAntwoorden()
    {
        final ImageButton image = (ImageButton) findViewById(R.id.afbeelding);
        image.setImageResource( getResources().getIdentifier("smiley_sad", "drawable", getPackageName()));

        soundManager.resetQueue();
        soundManager.addQueue( "zin_oepsdatwasnietjuist");
        soundManager.playQueue();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 4; i++) {
                    ImageButton image = (ImageButton) findViewById(
                            getResources().getIdentifier(("woord" + i), "id", getPackageName())
                    );

                    image.setBackgroundDrawable(null);
                }

                image.setImageResource( getResources().getIdentifier("woord_" + woord.getWoord().toLowerCase(), "drawable", getPackageName()));
            }
        }, 2000);

        antwoorden.clear();
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
                Intent _intent = new Intent();
                setResult(RESULT_OK, _intent);
                finish();
            }
        }
    }
}

