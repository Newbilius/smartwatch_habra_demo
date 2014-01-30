package com.smartwatch_habra_demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.sonyericsson.extras.liveware.aef.control.Control;
import com.sonyericsson.extras.liveware.aef.registration.Registration;
import com.sonyericsson.extras.liveware.aef.widget.Widget;
import com.sonyericsson.extras.liveware.extension.util.SmartWatchConst;
import com.sonyericsson.extras.liveware.extension.util.widget.WidgetExtension;

public class DemoWidget extends WidgetExtension {

    public DemoWidget(Context context, String hostAppPackageName) {
        super(context, hostAppPackageName);
    }

    @Override
    public void onStartRefresh() { //Когда виджет становится видимым и/или обновляется.
        showBitmap(new DemoWidgetImage(mContext).getBitmap());
    }

    @Override
    public void onStopRefresh() { //Когда виджет перестаёт быть видимым. Нам ничего не нужно делать, мы и так не обновляем его и не анимируем.
    }

    @Override
    public void onTouch(final int type, final int x, final int y) {
        if (!SmartWatchConst.ACTIVE_WIDGET_TOUCH_AREA.contains(x, y)) { //если кликнули вне иконки приложения - ничего не делаем
            return;
        }

        //по клику (быстрому или долгому) запускаем основное окно программы
        if (type == Widget.Intents.EVENT_TYPE_SHORT_TAP || type==Widget.Intents.EVENT_TYPE_LONG_TAP) {
            Intent intent = new Intent(Control.Intents.CONTROL_START_REQUEST_INTENT);
            intent.putExtra(Control.Intents.EXTRA_AEA_PACKAGE_NAME, mContext.getPackageName());
            intent.setPackage(mHostAppPackageName);
            mContext.sendBroadcast(intent, Registration.HOSTAPP_PERMISSION);
        }
    }
}