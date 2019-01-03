package be.thomasmore.logopedieproject.Classes;

import android.content.Context;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.logopedieproject.Activities.MainActivity;
import be.thomasmore.logopedieproject.R;

public  class SoundManager implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private Context context;
    private List<Integer> resourceIds = new ArrayList<Integer>();
    private int index = 0;

    public SoundManager(Context _context)
    {
        context = _context;
    }

    public void Play(String filename) // bv. definitie_kamp
    {
        filename = filename.toLowerCase();
        int resourceId = context.getResources().getIdentifier(filename, "raw", context.getPackageName());

        if( resourceId > 0) {
            System.out.println("SoundManager: Playing: " + resourceId);

            mediaPlayer = MediaPlayer.create(context, resourceId);
            mediaPlayer.start();
        }
    }



    public void resetQueue()
    {

        index = 0;
        resourceIds.clear();

    }
    public void addQueue(String filename)
    {
        System.out.println("SoundManager: Adding to queue: " + filename);
        filename = filename.toLowerCase();
        int resourceId = context.getResources().getIdentifier(filename, "raw", context.getPackageName());


        if( resourceId > 0) {
            System.out.println("SoundManager: Found: " + filename);

            resourceIds.add(resourceId);
        }
    }

    public void playQueue()
    {
        System.out.println("SoundManager: Playing queue: " + resourceIds.get(index) + " (" + (index +1) + "/" + resourceIds.size() +  ")");

        mediaPlayer = MediaPlayer.create(context, resourceIds.get(0));
        mediaPlayer.start();
        index++;
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (index < resourceIds.size()) {
            mp.release();
            mp = MediaPlayer.create(context, resourceIds.get(index));
            System.out.println("SoundManager: Playing queue: " + resourceIds.get(index) + " (" + (index + 1) + "/" + resourceIds.size() + ")");
            mp.start();
            mp.setOnCompletionListener(this);
            index++;
        }
    }


    public void stopPlaying(){mediaPlayer.stop(); };
    public boolean isPlaying()
    {
        return mediaPlayer.isPlaying();
    }

}
