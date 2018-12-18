package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import java.util.Timer;
import java.util.TimerTask;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.R;

import static java.lang.Thread.sleep;

public class Oefening4Activity extends AppCompatActivity implements ImageView.OnClickListener {
    private Kind kind;
    private Woord woord;
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
        antwoorden.add(woord);



        if(antwoorden.size() == 3)
            checkAntwoorden();
    }

    private void checkAntwoorden()
    {
        boolean correct = true;

        for(int i = 0; i < antwoorden.size(); i++)
        {
            if(antwoorden.get(i) == fouteWoord)
                correct =false;
        }


        if(correct)
            onCorrecteAntwoorden();
        else
            onFouteAntwoorden();
    }

    private void onCorrecteAntwoorden()
    {
        Intent intent = new Intent(this, Oefening5Activity.class);

        intent.putExtra("kind", kind);
        intent.putExtra("woord", woord);

        startActivity(intent);
    }

    private void onFouteAntwoorden()
    {


        final ImageButton image = (ImageButton) findViewById(R.id.afbeelding);
        image.setImageResource( getResources().getIdentifier("smiley_sad", "drawable", getPackageName()));



        soundManager.ResetQueue();
        soundManager.AddQueue( "zin_oepsdatwasnietjuist");
        soundManager.PlayQueue();

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
}
