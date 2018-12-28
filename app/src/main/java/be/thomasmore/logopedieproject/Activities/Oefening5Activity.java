package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.R;

public class Oefening5Activity extends AppCompatActivity {
    private Kind kind;
    private Woord woord;
    private Oefening oefening;
    SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        woord = (Woord) intent.getSerializableExtra("woord");
        kind = (Kind) intent.getSerializableExtra("kind");
        oefening = (Oefening) intent.getSerializableExtra("oefening");

        soundManager = new SoundManager(this);

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
