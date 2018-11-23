package com.manappuram.dev.myserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service implements Runnable{

    private int count = 0;
    private Handler handler = new Handler();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w(App.SERVICE_TAG,"Service Created..and running..");
        handler.postDelayed(this,1*1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(App.SERVICE_TAG,"Service Destroyed");
        handler.removeCallbacks(this);
    }

    @Override
    public void run() {
        Log.w(App.SERVICE_TAG,"Count from Service is "+count);
        Intent intent = new Intent();
        intent.putExtra(App.COUNT,count);
        intent.setAction(App.ACTION);
        sendBroadcast(intent);
        count = count+1;
        handler.postDelayed(this,1*1000);
    }
}
