package eazylivings.com.eazylivings.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.preference.PreferenceManager;
import eazylivings.com.eazylivings.activities.services.WelcomeScreen;
import eazylivings.com.eazylivings.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       if(!prefs.getBoolean("loginStatus", false)){
           prefs.edit().putString("userName","Newbie.");
       }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent;
                mainIntent = new Intent(MainActivity.this, WelcomeScreen.class);

                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 1000);


    }
}