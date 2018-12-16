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
import java.util.List;

import be.thomasmore.logopedieproject.Adapters.AdapterKind;
import be.thomasmore.logopedieproject.Adapters.AdapterWoordInMeting;
import be.thomasmore.logopedieproject.Classes.WoordInMeting;
import be.thomasmore.logopedieproject.R;

public class NaMetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_na_meting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        List<WoordInMeting> gemetenWoorden = (List<WoordInMeting>) intent.getSerializableExtra("list");


        for (int i = 0; i < gemetenWoorden.size(); i++)
        {
            System.out.println("TEST: "+ gemetenWoorden.get(i).getWoordId() + ": " + gemetenWoorden.get(i).isJuistOfFout());
        }


        AdapterWoordInMeting adapterWoordInMeting = new AdapterWoordInMeting(getApplicationContext(), gemetenWoorden);


        ListView listViewWoorden = (ListView) findViewById(R.id.listviewWoordenInMeting);
        listViewWoorden.setAdapter(adapterWoordInMeting);
    }

}
