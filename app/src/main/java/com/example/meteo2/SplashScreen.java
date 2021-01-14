package com.example.meteo2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class SplashScreen extends Activity{
    private final int SPLASH_DISPLAY_LENGTH = 5000;

    FrameLayout layout;
    View splashView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        layout = new FrameLayout(getApplicationContext());

        splashView = new ImageView(getApplicationContext());
        ((ImageView) splashView).setImageResource(R.drawable.logometeo);
        layout.addView(splashView);
        layout.setBackgroundColor(-16776961);

        setContentView(layout);



        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this,Main2Activity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
