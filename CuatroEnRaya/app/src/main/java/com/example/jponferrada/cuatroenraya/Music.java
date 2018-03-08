package com.example.jponferrada.cuatroenraya;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by jponferrada on 29/11/17.
 */

public class Music {
    private static MediaPlayer music;


    public static void playMusic(Activity activity, int musicimotion) {
        music = MediaPlayer.create(activity,R.raw.musicimotion);
        music.start();
        music.setLooping(true);
    }

    public static void pauseMusic() {
        if(music != null){
            music.stop();
            music.release();
            music = null;
        }
    }


}
