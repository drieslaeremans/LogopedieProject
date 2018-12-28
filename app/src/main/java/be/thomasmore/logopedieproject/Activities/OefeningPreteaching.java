package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;

public class OefeningPreteaching extends AppCompatActivity {

    private Kind kind;
    private Woord woord;
    private SoundManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening_preteaching);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sm = new SoundManager(this);

        Intent intent = getIntent();
        this.woord = (Woord) intent.getSerializableExtra("woord");
        this.kind = (Kind) intent.getSerializableExtra("kind");

        setPreteaching();

    }

    /*
        Tekst nog voorzien voor preteachingplaat
        "...preteaching..."
        "...hier gaan we dan..."
     */

    private void setPreteaching() {
        ImageView imageViewPreteaching = (ImageView) findViewById(R.id.preteaching_plaat);
        imageViewPreteaching.setImageResource(
                getResources().getIdentifier("preteaching", "drawable", getPackageName())
        );

        sm.Play("juistecontext_" + woord.getWoord().toLowerCase());
    }

    public void onClickPreteaching(View v) {
        if (!sm.isPlaying()) {
            ImageView imageViewPreteaching = (ImageView) findViewById(R.id.preteaching_plaat);
            imageViewPreteaching.setImageResource(android.R.color.transparent);

            final TextView textView = (TextView) findViewById(R.id.preteaching_timer);

            new CountDownTimer(4000, 1000) {
                public void onTick(long millisUntilFinished) {
                    textView.setText("" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    startOefeningen();
                }
            }.start();
        }
    }

    private void startOefeningen() {
        Intent intent = new Intent(this, Oefening1Activity.class);
        intent.putExtra("kind", this.kind);
        intent.putExtra("woord", this.woord);


        startActivity(intent);
    }



}
