package com.bw.movie.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bw.movie.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    int time=5;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.arg1==0){
                Intent intent = new Intent(MainActivity.this, YdyActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        SharedPreferences sp = getSharedPreferences("name", MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        boolean jump = sp.getBoolean("true",false);
//
//        if (jump){
//            Intent intent = new Intent(MainActivity.this, YdyActivity.class);
//            startActivity(intent);
//            finish();
//        }else{
//            edit.putBoolean("true",true);
//            edit.commit();
//            Timer timer = new Timer();
//            timer.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    time--;
//                    handler.sendEmptyMessage(0);
//                }
//            }, 0,1000);
//        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                time--;
                Message message = new Message();
                message.arg1=time;
                handler.sendMessage(message);
            }
        },0,1000);
    }
}
