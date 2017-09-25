package com.search.hinaikhan.newyorktimessearch.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.search.hinaikhan.newyorktimessearch.R;
import com.search.hinaikhan.newyorktimessearch.common.Constant;
import com.search.hinaikhan.newyorktimessearch.mvp.SearchActivity;

import java.util.Timer;
import java.util.TimerTask;

/*
SplashActivity class shows splash screen
 */

/**
 * Created by hinaikhan on 9/17/17.
 */

public class SplashActivity extends AppCompatActivity{

    private static final String TAG = SplashActivity.class.getSimpleName();

    /**
     * onCreate method for splash screen
     * @param savedInstanceState save UI state changes
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.splashScreenTheme);
        setContentView(R.layout.activity_splash);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Intent splashIntent;
                splashIntent = new Intent(SplashActivity.this, SearchActivity.class);
                startActivity(splashIntent);
                //kill the current activity
                finish();
            }
        };
        //show splash screen for 3 seconds
        new Timer().schedule(task, Constant.SPLASH_DELAY);



    }


}
