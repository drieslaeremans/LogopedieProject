package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class OefeningPreteaching extends AppCompatActivity {

    private Kind kind;
    private int groep;
    private Woord woord;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening_preteaching);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        soundManager = new SoundManager(this);

        Intent intent = getIntent();
        this.woord = (Woord) intent.getSerializableExtra("woord");
        this.kind = (Kind) intent.getSerializableExtra("kind");
        this.groep = intent.getIntExtra("groep", 1);
        setPreteaching();

    }

    /*
        Todo: Tekst nog voorzien voor preteachingplaat
        Todo: "Hallo ik ben Kaat. Ik ga vandaag samen met mijn vriendjes naar het bos,...
        op groot avontuur. Maar ik zoek nog een iemand die graag met mij mee
        wilt gaan. Samen gaan we op zoek naar heel veel nieuwe dingen.
        Wil jij graag met ons mee gaan? …. Ja? …  Super leuk! Klik maar op de
        bomen als je wilt starten.
        Todo: "Hier gaan we"
     */

    private void setPreteaching() {
        ImageView imageViewPreteaching = (ImageView) findViewById(R.id.preteaching_plaat);
        imageViewPreteaching.setImageResource(
                getResources().getIdentifier("preteaching", "drawable", getPackageName())
        );

        soundManager.Play("preteachingplaat");
    }

    public void onClickPreteaching(View v) {
        if (!soundManager.isPlaying()) {
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

        Oefening oefening = new Oefening();
        oefening.setOefenwoord(this.woord);
        oefening.setGroep(this.groep);


        intent.putExtra("oefening", oefening);

        startActivityForResult(intent, 1);
    }

    @Override
    public void onBackPressed() {
        soundManager.resetQueue();
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
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }



}
