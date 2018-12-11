package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.Helpers.DatabaseHelper;
import be.thomasmore.logopedieproject.R;

public class MetingActivity extends AppCompatActivity {

    private Kind kind;
    private DatabaseHelper db;
    private List<Woord> woorden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHelper(this);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);

        haalWoordenOp();
        leesKind(id);

        startMeting();
    }

    private void haalWoordenOp() {
        this.woorden = this.db.getWoorden();
    }

    private void leesKind(long id)
    {
        this.kind = this.db.getKind(id);
    }

    private void startMeting() {

    }

}
