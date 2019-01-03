package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject.Classes.Conditie;
import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Meting;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.Sessie;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.Classes.WoordInMeting;
import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;

public class DetailsKindActivity extends AppCompatActivity {

    private Kind kind;
    private DatabaseHelper db;

    Sessie sessie;
    Meting voormeting;
    List<WoordInMeting> voormetingWoorden;
    Meting nameting;
    List<WoordInMeting> nametingWoorden;
    Oefening oefening;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_kind);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.buttonOefeningen);
        button.setEnabled(false);

        button = (Button) findViewById(R.id.buttonNameting);
        button.setEnabled(false);


        button = (Button) findViewById(R.id.buttonResultaat);
        button.setEnabled(false);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);




        sessie = new Sessie();
        sessie.setKindId(id);
        sessie.setDatum(); // Huidige datum automatisch in setter toegepast

        if(id > 0)
            leesKind(id);

    }

    private void leesKind(long id)
    {
        this.kind = this.db.getKind(id);

        TextView naam = (TextView) findViewById(R.id.naam);
        naam.setText(this.kind.toString());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateKindDialog(kind);
            }
        });

        leesSessies();
    }

    private void leesSessies()
    {
        final ArrayList<String> items = new ArrayList<String>();
        final List<Sessie> dbItems = db.getSessies(kind.getId());

        for(int i = 0; i < dbItems.size(); i++)
            items.add(dbItems.get(i).getDatum());


        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(  this,
                android.R.layout.simple_list_item_1,
                items
        );

        ListView listView = (ListView) findViewById(R.id.sessies);


        listView.setAdapter(adapter);

        final Intent intent = new Intent(this, SessieOverzichtActivity.class);
        listView.setOnItemClickListener(
            new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> arg0, View view,
                                        int position, long id) {
                    Sessie sessie = dbItems.get(position);


                    intent.putExtra("sessieId", sessie.getId());
                    startActivity(intent);

                    //Take action here.
                }
            }
    );





    }

    private void updateKindDialog(final Kind kind)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View viewInflater = inflater.inflate(R.layout.dialog_homepage_kindtoevoegen, null);

        EditText editVoornaam   = (EditText) viewInflater.findViewById(R.id.voornaam);
        EditText editAchternaam = (EditText) viewInflater.findViewById(R.id.achternaam);


        editVoornaam.setText(kind.getVoornaam());
        editAchternaam.setText(kind.getAchternaam());

        builder.setMessage(R.string.overzichtkinderen_dialog_kindbewerken_message)
                .setTitle("Kind bewerken")
                .setView(viewInflater)
                .setPositiveButton(R.string.overzichtkinderen_dialog_kindbewerken_positive, null)
                .setNegativeButton(R.string.overzichtkinderen_dialog_kindbewerken_negative, null);

        final AlertDialog dialog = builder.create();
        dialog.show();


        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editVoornaam   = (EditText) viewInflater.findViewById(R.id.voornaam);
                EditText editAchternaam = (EditText) viewInflater.findViewById(R.id.achternaam);



                if(editVoornaam.getText().length() == 0)
                    editVoornaam.setError("Vul dit veld in2");

                if(editAchternaam.getText().length() == 0)
                    editAchternaam.setError("Vul dit veld in2");



                if(editAchternaam.getError() != "Vul dit veld in" && editVoornaam.getError() != "Vul dit veld in")
                {


                    kind.setVoornaam(editVoornaam.getText().toString());
                    kind.setAchternaam(editAchternaam.getText().toString());


                    if(db.updateKind(kind) == 1)
                    {
                        dialog.dismiss();
                        leesKind(kind.getId());
                    }

                }
            }
        });
    }

    public void onButtonClickStartVoormeting(View v) {
        Intent intent = new Intent(this, MetingActivity.class);
        intent.putExtra("id", this.kind.getId());

        startActivityForResult(intent, 1);
    }


    private void bepaalConditie(View viewInflater)
    {
        final Spinner spinnerWoord = viewInflater.findViewById(R.id.woord);
        final Spinner spinnerGroep = viewInflater.findViewById(R.id.groep);

        final TextView training = viewInflater.findViewById(R.id.training);

        String woord = (String) spinnerWoord.getSelectedItem();
        int groep = Integer.parseInt (((String) spinnerGroep.getSelectedItem()).replace("Groep ", ""));

        Woord w = db.getWoordByNaam(woord);
        Conditie c = Conditie.ONGELDIGE_CONDITIE;
        if(w != null)
            c = Woord.bepaalConditie(groep, w);


        switch(c)
        {
            case TRAINEN_NIET_FONOLOGISCH_VERKENNEN:
                training.setText("Niet-fonologisch verkennen");
                break;
            case TRAINEN_FONOLOGISCH_VERKENNEN_ZOEMEND:
                training.setText("Fonologisch verkennen, zoemend");
                break;
            case TRAINEN_FONOLOGISCH_VERKENNEN_KLANKGROEPEN:
                training.setText("Fonologisch verkennen, klankgroepen");
                break;
            case ONGELDIGE_CONDITIE:
                training.setText("Niet van toepassing");
                break;
        }
    }

    public void onButtonClickStartOefeningen(View v) {
        System.out.println("Start oefeningen");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View viewInflater = inflater.inflate(R.layout.dialog_detailskind_oefeningen, null);

        final Spinner spinnerWoord = viewInflater.findViewById(R.id.woord);
        final Spinner spinnerGroep = viewInflater.findViewById(R.id.groep);

        final Intent intentPreteachPlaat = new Intent(this,  OefeningPreteaching.class);

        List<Woord> woorden = db.getDoelwoorden();
        String[] woordenArray = new String[woorden.size()];

        for(int i =0; i < woorden.size(); i++)
        {
            woordenArray[i] = woorden.get(i).getWoord();
        }


        String[] groepenArray = { "Groep 1", "Groep 2", "Groep 3"};

        final ArrayAdapter<String> spinnerWoordAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,  woordenArray);
        spinnerWoordAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWoord.setAdapter(spinnerWoordAdapter);


        final ArrayAdapter<String> spinnerGroepAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,  groepenArray);
        spinnerGroepAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroep.setAdapter(spinnerGroepAdapter);


        spinnerWoord.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,  int arg2, long arg3) {
                bepaalConditie(viewInflater);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        spinnerGroep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,  int arg2, long arg3) {
                bepaalConditie(viewInflater);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        builder.setMessage(R.string.oefeningen_dialog_woordengroep_message)
                .setTitle("Oefeningen")
                .setView(viewInflater)
                .setPositiveButton(R.string.oefeningen_dialog_woordengroep_positive, null)
                .setNegativeButton(R.string.oefeningen_dialog_woordengroep_negative, null);

        final AlertDialog dialog = builder.create();
        dialog.show();


        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Woord woord =  db.getWoordByNaam((String) spinnerWoord.getSelectedItem());
                int groep = Integer.parseInt (((String) spinnerGroep.getSelectedItem()).replace("Groep ", ""));


                if(woord != null)
                {
                    dialog.cancel();

                    intentPreteachPlaat.putExtra("kind", kind);
                    intentPreteachPlaat.putExtra("woord", woord);
                    intentPreteachPlaat.putExtra("groep", groep);
                    startActivityForResult(intentPreteachPlaat, 2);
                }
            }
        });

    }


    public void onButtonClickStartNameting(View v) {
        Intent intent = new Intent(this, MetingActivity.class);
        intent.putExtra("id", this.kind.getId());

        startActivityForResult(intent, 3);
    }

    public void onButtonClickSessieOpslagen(View v)
    {
        if(this.oefening == null)
        {
            System.out.println("Oefening is null");
            return;
        }
        System.out.println("Oefening: "+oefening);

        long id = db.sessieOpslagen(sessie, voormeting, voormetingWoorden, nameting, nametingWoorden, oefening );


        Button button = (Button) findViewById(R.id.buttonResultaat);
        button.setEnabled(false);


        System.out.println("Id : " +id);

        Intent intent = new Intent(this, SessieOverzichtActivity.class);
        intent.putExtra("sessieId", id);

        startActivityForResult(intent, 4);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        System.out.println("REQUESTCODE: " + requestCode);

        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                voormeting = (Meting) intent.getSerializableExtra("meting");
                voormetingWoorden = (List<WoordInMeting>) intent.getSerializableExtra("list");

                Button button = (Button) findViewById(R.id.buttonVoormeting);
                button.setEnabled(false);

                button = (Button) findViewById(R.id.buttonOefeningen);
                button.setEnabled(true);
            }
        } else if(requestCode == 2) {
            if (resultCode == RESULT_OK) {
                oefening = (Oefening) intent.getSerializableExtra("oefening");

                Button button = (Button) findViewById(R.id.buttonOefeningen);
                button.setEnabled(false);

                button = (Button) findViewById(R.id.buttonNameting);
                button.setEnabled(true);

                button = (Button) findViewById(R.id.buttonResultaat);
                button.setEnabled(false);
            }
        } else if(requestCode == 3) {
            if (resultCode == RESULT_OK) {
                nameting = (Meting) intent.getSerializableExtra("meting");
                nametingWoorden = (List<WoordInMeting>) intent.getSerializableExtra("list");



                Button button = (Button) findViewById(R.id.buttonNameting);
                button.setEnabled(false);

                button = (Button) findViewById(R.id.buttonResultaat);
                button.setEnabled(true);
                button.setBackgroundColor(Color.GREEN);
            }
        } else if(requestCode == 4) {
            finish();
        }
    }
}
