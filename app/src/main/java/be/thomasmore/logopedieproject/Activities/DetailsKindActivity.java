package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

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
       // button.setEnabled(false);
        // effe afgezet, anders kan ik ni testen :D
        button = (Button) findViewById(R.id.buttonNameting);
        button.setEnabled(false);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);

        sessie = new Sessie();
        sessie.setKindId(id);
        sessie.setDatum(""); // Huidige datum automatisch in setter toegepast

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

    public void onButtonClickStartOefeningen(View v) {
        Intent intent = new Intent(this, OefeningPreteaching.class);
        intent.putExtra("kind", this.kind);

        Woord testwoord = db.getOefenwoord();
        intent.putExtra("woord", testwoord);


        startActivityForResult(intent, 2);
    }


    public void onButtonClickStartNameting(View v) {
        Intent intent = new Intent(this, MetingActivity.class);
        intent.putExtra("id", this.kind.getId());

        startActivityForResult(intent, 3);
    }

    public void onButtonClickSessieOpslagen(View v) {

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
                voormeting = (Meting) intent.getSerializableExtra("meting");
                voormetingWoorden = (List<WoordInMeting>) intent.getSerializableExtra("list");

                Button button = (Button) findViewById(R.id.buttonVoormeting);
                button.setEnabled(false);

                button = (Button) findViewById(R.id.buttonOefeningen);
                button.setEnabled(true);
            }
        } else if(requestCode == 2) {
            if (resultCode == RESULT_OK) {
                //Todo: Oefeningen afmaken en bij elke oef oefening-object resultaat bijhouden.
                //Todo: Na-oefening activity maken die resultaat van oefening toont?
                //Todo: Vanuit Na-oefening activity het oefening-object meegeven als intent (analoog aan Nameting->meting->this)
                oefening = (Oefening) intent.getSerializableExtra("oefening");

                Button button = (Button) findViewById(R.id.buttonOefeningen);
                button.setEnabled(false);

                button = (Button) findViewById(R.id.buttonNameting);
                button.setEnabled(true);
            }
        } else if(requestCode == 3) {
            if (resultCode == RESULT_OK) {
                nameting = (Meting) intent.getSerializableExtra("meting");
                nametingWoorden = (List<WoordInMeting>) intent.getSerializableExtra("list");

                Button button = (Button) findViewById(R.id.buttonNameting);
                button.setEnabled(false);
            }
        }
    }
}
