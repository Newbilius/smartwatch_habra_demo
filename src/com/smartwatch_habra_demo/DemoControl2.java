package com.smartwatch_habra_demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.extension.util.control.ControlTouchEvent;

public class DemoControl2 extends DemoControl {
    static final Rect buttonStopPlaySmartWatch2 = new Rect(59, 52, 167, 122);

    public DemoControl2(Context context, String hostAppPackageName) {
        super(context, hostAppPackageName);
    }

    @Override
    public void onTouch(final ControlTouchEvent event) {//реакция на клики
        if (event.getAction() == Control.Intents.CLICK_TYPE_SHORT) {
            if (buttonStopPlaySmartWatch2.contains(event.getX(), event.getY())){
                MusicBackgroundControlWrapper.TogglePausePlay(mContext);
            }
        }
    }

    @Override
    public void onResume() {//рисуем изображение
        Bitmap mPicture = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.control_picture2);
        showBitmap(mPicture);
    }
}