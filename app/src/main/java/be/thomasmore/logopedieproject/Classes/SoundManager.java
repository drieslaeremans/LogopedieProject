package be.thomasmore.logopedieproject.Classes;

import android.content.Context;
import android.media.MediaPlayer;

import be.thomasmore.logopedieproject.Activities.MainActivity;
import be.thomasmore.logopedieproject.R;

public class SoundManager {
    public static void Play(Context context, String filename) // bv. definitie_kamp
    {

        int resourceId = context.getResources().getIdentifier(filename, "raw", context.getPackageName());


        MediaPlayer sound = MediaPlayer.create(context, resourceId);
        sound.start();
    }

}
