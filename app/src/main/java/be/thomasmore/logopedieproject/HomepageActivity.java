package be.thomasmore.logopedieproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class HomepageActivity extends AppCompatActivity {

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                voegKindToeDialog();
            }
        });

        db = new DatabaseHelper(this);
        leesKinderen();
    }

    private void leesKinderen() {
        final List<Kind> kinderen = db.getKinderen();

        ArrayAdapter<Kind> adapter =
                new ArrayAdapter<Kind>(this,
                        android.R.layout.simple_list_item_1, kinderen);

        final ListView listViewKinderen =
                (ListView) findViewById(R.id.listViewKindjes);
        listViewKinderen.setAdapter(adapter);
    }

    public void voegKindToeDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        final View viewInflater = inflater.inflate(R.layout.dialog_homepage_kindtoevoegen, null);


        builder.setMessage(R.string.homepage_dialog_kindtoevoegen_message)
                .setTitle("Kind toevoegen")
                .setView(viewInflater)
                .setPositiveButton(R.string.homepage_dialog_kindtoevoegen_positive, null)
                .setNegativeButton(R.string.homepage_dialog_kindtoevoegen_negative, null);

        final AlertDialog dialog = builder.create();
        dialog.show();


        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    EditText editVoornaam   = (EditText) viewInflater.findViewById(R.id.voornaam);
                    EditText editAchternaam = (EditText) viewInflater.findViewById(R.id.achternaam);



                    if(editVoornaam.getText().length() == 0)
                        editVoornaam.setError("Vul dit veld in");

                    if(editAchternaam.getText().length() == 0)
                        editAchternaam.setError("Vul dit veld in");



                    if(editAchternaam.getError() != "Vul dit veld in" && editVoornaam.getError() != "Vul dit veld in")
                    {
                        System.out.println("Kind: oké");
                        // db create


                        Kind kind = new Kind();
                        kind.setVoornaam(editVoornaam.getText().toString());
                        kind.setAchternaam(editAchternaam.getText().toString());

                        long id = db.createKind(kind);
                        dialog.dismiss();
                    }





            }
        });
    }



}
