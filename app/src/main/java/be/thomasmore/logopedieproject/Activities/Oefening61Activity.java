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
import be.thomasmore.logopedieproject.R;

//TRAINEN_NIET_FONOLOGISCH_VERKENNEN
public class Oefening61Activity extends AppCompatActivity {
    private Kind kind;
    private Woord woord;
    private Oefening oefening;

    SoundManager soundManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening61);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        woord = (Woord) intent.getSerializableExtra("woord");
        kind = (Kind) intent.getSerializableExtra("kind");
        oefening = (Oefening) intent.getSerializableExtra("oefening");

        soundManager = new SoundManager(this);


        leesWoord();
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

    public void onSmileyClick(View v)
    {
        /*
        if(v.getTag() == ":)")
        else */
    }

    public void terugNaarDetailKindActivity(View v)
    {
        Intent intent = new Intent();
        oefening.setOefening6(true);

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
