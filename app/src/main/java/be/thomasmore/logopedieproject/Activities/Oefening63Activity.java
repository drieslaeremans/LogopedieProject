package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;

public class Oefening63Activity extends AppCompatActivity {
    private Kind kind;
    private Woord woord;
    private Oefening oefening;

    SoundManager soundManager;


    int screenWidth, screenHeight;
    int aantalKeerSpringen = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening63);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        woord = (Woord) intent.getSerializableExtra("woord");
        kind = (Kind) intent.getSerializableExtra("kind");
        oefening = (Oefening) intent.getSerializableExtra("oefening");


        soundManager = new SoundManager(this);


        aantalKeerSpringen = woord.getLettergrepen().split("-").length;

        leesWoord();
        bepaalSchermAfmetingen();

    }


    public void onKonijnClick(View v)
    {
        startAnimatieKonijn();
        soundManager.Play("woord_" + woord.getWoord());

    }

    private void bepaalSchermAfmetingen()
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
    }

    private void startAnimatieKonijn()
    {
        final ImageView konijn = (ImageView) findViewById(R.id.konijn);
        final Handler handler = new Handler();

        konijn.post(
            new Runnable() {
                @Override
                public void run() {
                    TranslateAnimation animatieBeweging = new TranslateAnimation(0.0f, 0.0f, 0.0f, -100.f /*konijn.getMeasuredHeight()*/);
                    animatieBeweging.setDuration(250);
                    animatieBeweging.setRepeatMode(2);
                    animatieBeweging.setRepeatCount( aantalKeerSpringen );
                    konijn.startAnimation(animatieBeweging);
                }
            }
        );

    }

    private void leesWoord()
    {
        ImageView img = (ImageView) findViewById(R.id.woordfoto);
        TextView text = (TextView) findViewById(R.id.woord);

        img.setImageResource(
                getResources().getIdentifier("woord_"+woord.getWoord().toLowerCase(), "drawable", getPackageName())
        );

        text.setText(woord.getLettergrepen());
    }

    public void terugNaarDetailKindActivity(View v)
    {
        Intent intent = new Intent();
        oefening.setOefening6(true);

        intent.putExtra("oefening", oefening);
        setResult(RESULT_OK);

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
