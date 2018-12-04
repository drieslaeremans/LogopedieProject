package be.thomasmore.logopedieproject;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

}
