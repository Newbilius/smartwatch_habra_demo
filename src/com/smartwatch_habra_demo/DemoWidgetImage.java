package com.smartwatch_habra_demo;

import android.content.Context;
import android.widget.LinearLayout;
import com.sonyericsson.extras.liveware.extension.util.widget.SmartWatchWidgetImage;

public class DemoWidgetImage extends SmartWatchWidgetImage {

    public DemoWidgetImage(Context context) {
        super(context);
        setInnerLayoutResourceId(R.layout.music_widget_image);
    }

    @Override
    protected void applyInnerLayout(LinearLayout innerLayout) {
        //даже если ничего не делаем с содержимым - переопределить обязаны. Угу.
    }
}