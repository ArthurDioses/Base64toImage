package com.example.everis.poc_to_base64.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class JobService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        // api rest

    }

    ///
    private void cargarImagenes() {

        // broadcast
        sendBroadcast(new Intent("SERVICIO_FINALIZADO"));

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
