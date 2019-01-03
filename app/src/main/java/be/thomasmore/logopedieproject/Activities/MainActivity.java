package be.thomasmore.logopedieproject.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import be.thomasmore.logopedieproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void onButtonClickStart(View v) {
        Intent intent = new Intent(this, OverzichtKinderenActivity.class);
        startActivity(intent);

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


}
