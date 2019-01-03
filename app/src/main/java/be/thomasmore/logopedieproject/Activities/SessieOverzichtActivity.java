package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import be.thomasmore.logopedieproject.Classes.Sessie;
import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;

public class SessieOverzichtActivity extends AppCompatActivity {
    private Sessie sessie;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessie_overzicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();

        long sessieId = intent.getLongExtra("sessieId", 0);

        if(sessieId > 0)
        {
            sessie = db.getSessie(sessieId);
        }

        vulDataIn();
    }

    private void vulDataIn()
    {
        TextView kind= (TextView) findViewById(R.id.kind);

        kind.setText(String.valueOf(sessie.getKindId()));
    }

}
