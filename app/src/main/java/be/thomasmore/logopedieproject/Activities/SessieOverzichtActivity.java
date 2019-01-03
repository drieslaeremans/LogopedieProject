package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject.Adapters.AdapterOefeningen;
import be.thomasmore.logopedieproject.Adapters.AdapterWoordInMeting;
import be.thomasmore.logopedieproject.Adapters.OefeningListItem;
import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Meting;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.Sessie;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.Classes.WoordInMeting;
import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;

public class SessieOverzichtActivity extends AppCompatActivity {
    private Sessie sessie;
    private DatabaseHelper db;

    private Kind kind;
    private Oefening oefening;
    private List<WoordInMeting> voormetingWoorden;
    private List<WoordInMeting> nametingWoorden;

    private List<Woord> woorden;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessie_overzicht);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();

        long sessieId = intent.getLongExtra("sessieId", 0);

        if(sessieId > 0)
        {
            System.out.println("SessieID " + sessieId);
            sessie = db.getSessie(sessieId);
            kind = db.getKind(sessie.getKindId());


            oefening = db.getOefening(sessie.getOefeningId());

            voormetingWoorden   = db.getWoordenInMeting(sessie.getVoormetingId());
            nametingWoorden     = db.getWoordenInMeting(sessie.getNametingId());

            vulDataIn();
        }


    }

    private void vulDataIn()
    {
        TextView titel = (TextView) findViewById(R.id.kind);
        TextView datum = (TextView) findViewById(R.id.datum);
        TextView titelOefeningen = (TextView) findViewById(R.id.titelOefeningen);


        titel.setText(kind.toString());

        datum.setText(sessie.getDatum());


        // Voormeting
        AdapterWoordInMeting adapterWoordInMeting = new AdapterWoordInMeting(getApplicationContext(), voormetingWoorden);
        ListView listViewWoorden = (ListView) findViewById(R.id.listviewWoordenInMeting);
        listViewWoorden.setAdapter(adapterWoordInMeting);

        // Nameting
        AdapterWoordInMeting adapterWoordInMeting2 = new AdapterWoordInMeting(getApplicationContext(), nametingWoorden);
        ListView listViewWoorden2 = (ListView) findViewById(R.id.listviewWoordenInMeting2);
        listViewWoorden2.setAdapter(adapterWoordInMeting2);

        // Oefeningen

        List<OefeningListItem> list = new ArrayList<OefeningListItem>();
        list.add(new OefeningListItem("Oefening 1", oefening.isOefening1() ? "tick" : "cross") );
        list.add(new OefeningListItem("Oefening 2", oefening.isOefening2() ? "tick" : "cross") );
        list.add(new OefeningListItem("Oefening 3", oefening.isOefening3() ? "tick" : "cross") );
        list.add(new OefeningListItem("Oefening 4", oefening.isOefening4() ? "tick" : "cross") );
        list.add(new OefeningListItem("Oefening 5", oefening.isOefening5() ? "tick" : "cross") );
        list.add(new OefeningListItem("Oefening 6", oefening.isOefening6() ? "smiley_happy" : "smiley_sad") );


        titelOefeningen.setText("Oefening: "+ oefening.getOefenwoord().getWoord());
        AdapterOefeningen adapterOefeningen = new AdapterOefeningen(this, list);
        ListView listViewOefeningen = (ListView) findViewById(R.id.listviewOefening);
        listViewOefeningen.setAdapter(adapterOefeningen);



    }

}
