package com.smartwatch_habra_demo;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.SystemClock;
import android.view.KeyEvent;


public class MusicBackgroundControlWrapper {
    public static void KeyPressDownAndUp(int key,Context context){ //всегда вызывает только один из плееров, принципа выбора не понял. блин.
        long eventtime = SystemClock.uptimeMillis() - 1;

        Intent downIntent = new Intent(Intent.ACTION_MEDIA_BUTTON, null);
        KeyEvent downEvent = new KeyEvent(eventtime, eventtime,
                KeyEvent.ACTION_DOWN, key, 0);
        downIntent.putExtra(Intent.EXTRA_KEY_EVENT, downEvent);
        context.sendOrderedBroadcast(downIntent, null);

        eventtime++;
        Intent upIntent = new Intent(Intent.ACTION_MEDIA_BUTTON, null);
        KeyEvent upEvent = new KeyEvent(eventtime, eventtime,
                KeyEvent.ACTION_UP, key, 0);
        upIntent.putExtra(Intent.EXTRA_KEY_EVENT, upEvent);
        context.sendOrderedBroadcast(upIntent, null);
    }

    public static void VolumeUp(Context context){
        AudioManager audioManager =(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        int max=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        if (current<max){
            current++;
        }

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                current,0);
    }

    public static void VolumeDown(Context context){
        AudioManager audioManager =(AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        int current=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        if (current>0){
            current--;
        }

        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                current,0);
    }

    public static void TogglePausePlay(Context context){
        KeyPressDownAndUp(KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE,context);
    }

    public static void Next(Context context){
        KeyPressDownAndUp(KeyEvent.KEYCODE_MEDIA_NEXT, context);
    }

    public static void Prev(Context context){
        KeyPressDownAndUp(KeyEvent.KEYCODE_MEDIA_PREVIOUS, context);
    }
}