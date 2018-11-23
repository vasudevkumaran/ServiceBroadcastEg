package com.manappuram.dev.myserviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Runnable {

    private int count = 0;
    private Handler handler = new Handler();
    private TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startBtn = (Button)findViewById(R.id.btnStart);
        Button stopBtn = (Button)findViewById(R.id.btnStop);
        countView = (TextView)findViewById(R.id.countTv);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });
        //handler.postDelayed(this,1000);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(App.ACTION);
        registerReceiver(myReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //handler.removeCallbacks(this);
        unregisterReceiver(myReceiver);
    }

    @Override
    public void run() {
        Log.w(App.ACTIVITY_TAG,"Count from Activity is "+count);
        count = count+1;
        handler.postDelayed(this,1000);
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            countView.setText(Integer.toString(bundle.getInt(App.COUNT)));
        }
    };
}
