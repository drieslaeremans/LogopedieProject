package be.thomasmore.logopedieproject.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import be.thomasmore.logopedieproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MediaPlayer sound = MediaPlayer.create(MainActivity.this,R.raw.definitie_duikbril);
        sound.start();

    }

    public void onButtonClickStart(View v) {
        Intent intent = new Intent(this, OverzichtKinderenActivity.class);
        startActivity(intent);
    }

    public void onButtonClickInstellingen(View v) {
//        Intent intent = new Intent(this, OverzichtKinderenActivity.class);
//        startActivity(intent);
    }

    public void onButtonClickAfsluiten(View v) {
        exitApplicationDialog();
    }



    private void exitApplicationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.mainactivity_dialog_afsluiten_message)
                .setPositiveButton(R.string.mainactivity_dialog_afsluiten_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        System.exit(0);
                    }
                }).setNegativeButton(R.string.mainactivity_dialog_afsluiten_negative, null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
