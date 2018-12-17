package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject.Adapters.AdapterKind;
import be.thomasmore.logopedieproject.Adapters.AdapterWoordInMeting;
import be.thomasmore.logopedieproject.Classes.Meting;
import be.thomasmore.logopedieproject.Classes.WoordInMeting;
import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;

public class NaMetingActivity extends AppCompatActivity {

    DatabaseHelper db;

    private List<WoordInMeting> gemetenWoorden = new ArrayList<>();
    private Meting meting = new Meting();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_na_meting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);

        Intent intent = getIntent();

        meting = (Meting) intent.getSerializableExtra("meting");
        gemetenWoorden = (List<WoordInMeting>) intent.getSerializableExtra("list");
        for (int i = 0; i < gemetenWoorden.size(); i++)
        {
            System.out.println("TEST: "+ gemetenWoorden.get(i).getWoordId() + ": " + gemetenWoorden.get(i).isJuistOfFout());
        }

        AdapterWoordInMeting adapterWoordInMeting = new AdapterWoordInMeting(getApplicationContext(), gemetenWoorden);

        ListView listViewWoorden = (ListView) findViewById(R.id.listviewWoordenInMeting);
        listViewWoorden.setAdapter(adapterWoordInMeting);
    }

    public void buttonClickMetingOpslaan(View v) {
        long metingId = db.createMeting(meting);

        for (WoordInMeting gemetenWoord : gemetenWoorden) {
            gemetenWoord.setMetingId(metingId);
        }
        db.createWoordenInMeting(gemetenWoorden);

        activitySluitenNaOpslaan();
    }

    private void activitySluitenNaOpslaan() {
        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }

    public void activitySluitenNietOpslaan() {
        Intent intent = new Intent();

        setResult(RESULT_CANCELED, intent);
        finish();
    }

}
