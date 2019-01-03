package be.thomasmore.logopedieproject.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import be.thomasmore.logopedieproject.Classes.Conditie;
import be.thomasmore.logopedieproject.Classes.Kind;
import be.thomasmore.logopedieproject.Classes.Oefening;
import be.thomasmore.logopedieproject.Classes.SoundManager;
import be.thomasmore.logopedieproject.Classes.Woord;
import be.thomasmore.logopedieproject.R;

public class Oefening5Activity extends AppCompatActivity implements LinearLayout.OnClickListener{
    private Kind kind;
    private Woord woord;
    private Oefening oefening;

    private String fouteWoord;
    private List<String> woorden = new ArrayList<String>();
    private String selectedWoord = null;

    private LinearLayout containerJuist, containerFout;
    int poging = 1;

    SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oefening5);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        woord = (Woord) intent.getSerializableExtra("woord");
        kind = (Kind) intent.getSerializableExtra("kind");
        oefening = (Oefening) intent.getSerializableExtra("oefening");

        soundManager = new SoundManager(this);


        for (int i = 0; i <= 3; i++)
        {
            woorden.add( "oef5_" + woord.getWoord().toLowerCase() + "_" + i );
        }
        fouteWoord = woorden.get( woorden.size()-1 );
        Collections.shuffle(woorden);

        leesFotos();



        containerJuist = (LinearLayout) findViewById(R.id.container_juist);
        containerJuist.setTag("Juist");
        containerJuist.setOnClickListener(this);
        containerFout = (LinearLayout) findViewById(R.id.container_fout);
        containerFout.setTag("Fout");
        containerFout.setOnClickListener(this);
    }

    private boolean isGeselecteerd(String woord)
    {
        for(int i = 0; i < containerJuist.getChildCount(); i++)
        {
            View v = containerJuist.getChildAt(i);

            if(v.getTag() == woord)
                return true;
        }

        for(int i = 0; i < containerFout.getChildCount(); i++)
        {
            View v = containerFout.getChildAt(i);

            if(v.getTag() == woord)
                return true;
        }

        return false;
    }

    private void setImageDisabled(String woord, boolean disabled)
    {
        ImageButton button;
        for (int i = 0; i <= 3; i++)
        {
            button = findViewById(
                    getResources().getIdentifier(("woord" + i), "id", getPackageName())
            );

            if(button.getTag() == woord)
            {
                if(disabled)
                    button.setColorFilter(Color.argb(150,200,200,200));
                else
                    button.clearColorFilter();
            }
        }
    }

    @Override
    // Wanneer er op de groene of rode LinearLayout geklikt wordt
    public void onClick(View v)
    {
        System.out.println("Oefening5: selected container: " + v.getTag());

        if(selectedWoord == null)
            return;


        if(isGeselecteerd(selectedWoord))
            return;


        ImageButton image = new ImageButton(this);
        image.setTag(selectedWoord);
        image.setImageResource(
                getResources().getIdentifier( selectedWoord, "drawable", getPackageName())
        );
        if(v.getTag() == "Juist")
            containerJuist.addView(image);
        else
            containerFout.addView(image);



        setImageDisabled(selectedWoord, true);
        image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        image.getLayoutParams().height = 200;
        image.getLayoutParams().width = 200;
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout ll = (LinearLayout) v.getParent();
                ll.removeView(v);

                setImageDisabled((String)v.getTag(), false);
            }
        });

        checkAntwoorden();
    }

    private void onFouteAntwoorden()
    {
        System.out.println("Oefening5: onFouteAntwoorden");


        soundManager.resetQueue();
        soundManager.addQueue( "zin_oepsdatwasnietjuist");
        soundManager.playQueue();

        for(int i = 0; i < woorden.size(); i++)
        {
            setImageDisabled(woorden.get(i), false);
        }

        containerJuist.removeAllViews();
        containerFout.removeAllViews();
    }

    private void onJuisteAntwoorden()
    {
        System.out.println("Oefening5: onJuisteAntwoorden");




        Conditie conditie = oefening.bepaalConditie();
        Intent intent;

        switch(conditie)
        {
            case TRAINEN_NIET_FONOLOGISCH_VERKENNEN:
                intent = new Intent(this, Oefening61Activity.class);
                break;

            case TRAINEN_FONOLOGISCH_VERKENNEN_ZOEMEND:
                intent = new Intent(this, Oefening62Activity.class);
                break;

            case TRAINEN_FONOLOGISCH_VERKENNEN_KLANKGROEPEN:
                intent = new Intent(this, Oefening63Activity.class);
                break;

            default:
                intent =  new Intent(this, MainActivity.class);
                break;
        }




        System.out.println("Oefening5: resultaat is " + (poging==1));
        oefening.setOefening5( (poging == 1) );
        intent.putExtra("kind", kind);
        intent.putExtra("woord", woord);
        intent.putExtra("oefening", oefening);

        startActivityForResult(intent, 1);
    }

    private void checkAntwoorden()
    {
        if(containerFout.getChildCount() + containerJuist.getChildCount() == 4)
        {
            if(containerFout.getChildCount() == 1)
            {
                if(containerFout.getChildAt(0).getTag() == fouteWoord)
                {
                    onJuisteAntwoorden();
                    return;
                }
            }
            poging++;
            onFouteAntwoorden();
        }
        // Niets doen, waarschijnlijk nog niet alle elementen aangeduid
    }

    private void leesFotos()
    {
        TextView doelwoord = (TextView) findViewById(R.id.woord);
        doelwoord.setText(woord.getWoord());

        ImageButton image;

        for (int i = 0; i < 4; i++)
        {
            image = (ImageButton) findViewById(
                    getResources().getIdentifier(("woord" + i), "id", getPackageName())
            );


            image.setTag(woorden.get(i));

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageButton button = (ImageButton) v;
                    selectedWoord = (String) button.getTag();
                    System.out.println("Oefening5: selected woord: " + selectedWoord);
                }
            });
            image.setPadding(15,15,15,15);
            image.setImageResource(
                    getResources().getIdentifier(woorden.get(i), "drawable", getPackageName())
            );
        }
    }



    @Override
    public void onBackPressed() {
        soundManager.resetQueue();
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == RESULT_CANCELED) {
                setResult(RESULT_CANCELED);
                finish();
            } else if (resultCode == RESULT_OK) {
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

}
