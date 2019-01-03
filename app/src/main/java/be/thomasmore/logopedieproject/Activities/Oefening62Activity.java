package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.R;

public class Oefening62Activity extends AppCompatActivity {
    private Kind kind;
    private Woord woord;
    private Oefening oefening;

    SoundManager soundManager;


    int screenWidth, screenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening62);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        woord = (Woord) intent.getSerializableExtra("woord");
        kind = (Kind) intent.getSerializableExtra("kind");
        oefening = (Oefening) intent.getSerializableExtra("oefening");

        soundManager = new SoundManager(this);


        leesWoord();
        bepaalSchermAfmetingen();
        startAnimatieBij();

        speelGeluid();

        soundManager.Play("buzz");
        soundManager.Play("woord_" + woord.getWoord());


        timerVolgendeIntent();

    }

    private void timerVolgendeIntent()
    {
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

    
            }
        }, 7000);
    }

    private void speelGeluid()
    {

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                soundManager.Play("woord_" + woord.getWoord());
                speelGeluid();
            }
        }, 4500);


    }

    private void bepaalSchermAfmetingen()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
    }

    private void startAnimatieBij()
    {
        final ImageView bij = (ImageView) findViewById(R.id.bij);
        final Handler handler = new Handler();

        bij.post(
            new Runnable() {
                @Override
                public void run() {

                    TranslateAnimation animatieBeweging = new TranslateAnimation(0.0f, screenWidth - (bij.getMeasuredWidth()), 0.0f, 0.0f);
                    animatieBeweging.setDuration(3500);
                    animatieBeweging.setRepeatMode(2);
                    animatieBeweging.setRepeatCount(999999);

                    bij.startAnimation(animatieBeweging);

                }
            }
        );

        flipBij();;
    }

    private void flipBij()
    {
        final ImageView bij = (ImageView) findViewById(R.id.bij);
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bij.setScaleX( bij.getScaleX()*-1 );

                flipBij();
            }
        }, 3500);

    }


    private void leesWoord()
    {
        ImageView img = (ImageView) findViewById(R.id.woordfoto);
        TextView text = (TextView) findViewById(R.id.woord);

        img.setImageResource(
                getResources().getIdentifier("woord_"+woord.getWoord().toLowerCase(), "drawable", getPackageName())
        );

        text.setText(woord.getWoord());
    }
    public void terugNaarDetailKindActivity(View v)
    {
        oefening.setOefening6(true);

        Intent intent = new Intent();
        intent.putExtra("oefening", oefening);
        setResult(RESULT_OK, intent);

        finish();
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
