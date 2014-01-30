package com.smartwatch_habra_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;

public class DemoControl extends ControlExtension {

    static final Rect buttonStopPlaySmartWatch = new Rect(43, 42, 85, 88);

    public DemoControl(Context context, String hostAppPackageName) {
        super(context, hostAppPackageName);
    }

    @Override
    public void onTouch(final ControlTouchEvent event) {//реакция на клики
        if (event.getAction() == Control.Intents.CLICK_TYPE_SHORT) {
            if (buttonStopPlaySmartWatch.contains(event.getX(), event.getY())){
                MusicBackgroundControlWrapper.TogglePausePlay(mContext);
            }
        }
    }

    @Override
    public void onSwipe(int direction) {//реакция на жесты
        if (direction== Control.Intents.SWIPE_DIRECTION_UP){
            MusicBackgroundControlWrapper.VolumeUp(mContext);
        }
        if (direction==Control.Intents.SWIPE_DIRECTION_DOWN){
            MusicBackgroundControlWrapper.VolumeDown(mContext);
        }
        if (direction==Control.Intents.SWIPE_DIRECTION_LEFT){
            MusicBackgroundControlWrapper.Next(mContext);
        }
        if (direction==Control.Intents.SWIPE_DIRECTION_RIGHT){
            MusicBackgroundControlWrapper.Prev(mContext);
        }
    }

    @Override
    public void onResume() {//рисуем изображение
        Bitmap mPicture = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.control_picture);
        showBitmap(mPicture);
    }
}