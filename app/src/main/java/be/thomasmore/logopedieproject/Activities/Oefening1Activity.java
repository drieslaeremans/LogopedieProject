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
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.Classes.WoordInMeting;
import be.thomasmore.logopedieproject.R;

public class Oefening1Activity extends AppCompatActivity {
    private Kind kind;
    private Woord woord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();

        kind = (Kind) intent.getSerializableExtra("kind");
        woord = (Woord) intent.getSerializableExtra("woord");

        leesWoord();
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
}
