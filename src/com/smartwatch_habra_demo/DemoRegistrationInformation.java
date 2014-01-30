package com.smartwatch_habra_demo;

import android.content.ContentValues;
import android.content.Context;
import com.sonyericsson.extras.liveware.aef.registration.Registration;
import com.sonyericsson.extras.liveware.extension.util.ExtensionUtils;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;

public class DemoRegistrationInformation extends RegistrationInformation {
    public static final int WIDGET_WIDTH_SMARTWATCH = 128;
    public static final int WIDGET_HEIGHT_SMARTWATCH = 110;

    public static final int CONTROL_WIDTH_SMARTWATCH = 128;
    public static final int CONTROL_HEIGHT_SMARTWATCH = 128;
    public static final int CONTROL_WIDTH_SMARTWATCH_2 = 220;
    public static final int CONTROL_HEIGHT_SMARTWATCH_2 = 176;

    Context mContext;

    protected DemoRegistrationInformation(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context == null");
        }
        mContext = context;
    }

    @Override
    public ContentValues getExtensionRegistrationConfiguration() {
        String iconHostapp = ExtensionUtils.getUriString(mContext, R.drawable.icon);

        ContentValues values = new ContentValues();
        values.put(Registration.ExtensionColumns.CONFIGURATION_ACTIVITY,DemoConfigActivity.class.getName()); //активити, которое будет отображаться в меню "настройки расширения". Если оно нам не нужно - убираем параметр совсем.
        values.put(Registration.ExtensionColumns.CONFIGURATION_TEXT,"Настройки демо-расширения");//а это текст, отображащийся в качестве пункта меню программы управления часами. Если оно нам не нужно - убираем параметр совсем.

        values.put(Registration.ExtensionColumns.NAME, "Хабра-демо-расширение");//имя, отображаемое в списке приложений внутри программы управления часами
        values.put(Registration.ExtensionColumns.EXTENSION_KEY,DemoReceiverService.EXTENSION_KEY); //уникальный ключ расширения

        values.put(Registration.ExtensionColumns.HOST_APP_ICON_URI, iconHostapp); //иконка в списке приложений в телефоне
        values.put(Registration.ExtensionColumns.EXTENSION_ICON_URI, iconHostapp); //иконка в списке приложений на самих часах, в идеале 48x48
        values.put(Registration.ExtensionColumns.NOTIFICATION_API_VERSION,getRequiredNotificationApiVersion());//нужная версия механизма уведомлений
        values.put(Registration.ExtensionColumns.PACKAGE_NAME, mContext.getPackageName());

        return values;
    }

    @Override
    public int getRequiredNotificationApiVersion() { //нам не нужно управление нотификациями
        return 0;
    }

    @Override
    public int getRequiredSensorApiVersion() { //нам не нужна инфа с сенсоров вроде акселерометра
        return 0;
    }

    //---------------------------------------------
    //всё что нужно для поддержки виджета
    //---------------------------------------------

    @Override
    public boolean isWidgetSizeSupported(final int width, final int height) {
        return (width == WIDGET_WIDTH_SMARTWATCH && height == WIDGET_HEIGHT_SMARTWATCH);
    }

    @Override
    public int getRequiredWidgetApiVersion() { //для поддержки первых часов
        return 1;
    }

    //---------------------------------------------
    //всё что нужно для поддержки контроллера
    //---------------------------------------------

    @Override
    public int getRequiredControlApiVersion() { //для поддержки первых часов
        return 1;
    }

    @Override
    public int getTargetControlApiVersion() { //для поддержки второй версии часов
        return 2;
    }

    @Override
    public boolean isDisplaySizeSupported(int width, int height) {
        return (width == CONTROL_WIDTH_SMARTWATCH_2 && height == CONTROL_HEIGHT_SMARTWATCH_2)
                || (width == CONTROL_WIDTH_SMARTWATCH && height == CONTROL_HEIGHT_SMARTWATCH);
    }
}