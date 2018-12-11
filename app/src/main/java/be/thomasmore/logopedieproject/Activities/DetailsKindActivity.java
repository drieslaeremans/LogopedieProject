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
import android.widget.EditText;
import android.widget.TextView;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;

public class DetailsKindActivity extends AppCompatActivity {

    private Kind kind;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_kind);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();


        long id = intent.getLongExtra("id", 0);


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

        startActivityForResult(intent, 2);
    }

}
