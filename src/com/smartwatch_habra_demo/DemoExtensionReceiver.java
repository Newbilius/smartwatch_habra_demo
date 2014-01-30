package com.smartwatch_habra_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DemoExtensionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        intent.setClass(context, DemoReceiverService.class);
        context.startService(intent);
    }
}