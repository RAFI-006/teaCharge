package com.teashop.teacharge;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class LaunchScreenActivity extends AppCompatActivity {
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);

                    finish();
                } catch (Exception ignored) {
                    ignored.printStackTrace();
                }
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}