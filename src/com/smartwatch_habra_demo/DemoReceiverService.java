package com.smartwatch_habra_demo;

import com.sonyericsson.extras.liveware.extension.util.ExtensionService;
import com.sonyericsson.extras.liveware.extension.util.control.ControlExtension;
import com.sonyericsson.extras.liveware.extension.util.registration.DeviceInfoHelper;
import com.sonyericsson.extras.liveware.extension.util.registration.RegistrationInformation;
import com.sonyericsson.extras.liveware.extension.util.widget.WidgetExtension;

public class DemoReceiverService extends ExtensionService {

    public static final String EXTENSION_KEY = "com.smartwatch_habra_demo"; //todo не смог найти в документации подробностей о применимости, так что просто копипастим из примеров по шаблону "пакет.приложения.key"

    public DemoReceiverService() {
        super(EXTENSION_KEY);
    }

    @Override
    protected RegistrationInformation getRegistrationInformation() {
        return new DemoRegistrationInformation(this);
    }

    @Override
    protected boolean keepRunningWhenConnected() {//нам не нужно постоянно держать сервис работающим
        return false;
    }

    @Override
    public WidgetExtension createWidgetExtension(String hostAppPackageName) { //возвращаем объект виджета
        return new DemoWidget(this,hostAppPackageName);
    }

    @Override
    public ControlExtension createControlExtension(String hostAppPackageName) {//возвращаем объект основной программы
        boolean IsSmartWatch2= DeviceInfoHelper.isSmartWatch2ApiAndScreenDetected(
                this, hostAppPackageName);
        if (IsSmartWatch2){
            return new DemoControl2(this,hostAppPackageName);
        }else{
            return new DemoControl(this,hostAppPackageName);
        }
    }
}