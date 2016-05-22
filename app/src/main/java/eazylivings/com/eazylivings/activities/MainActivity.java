package eazylivings.com.eazylivings.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.activities.login.LoginPage;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        final boolean isUserLoggedIn= prefs.getBoolean("loginStatus",false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent;
                if(!isUserLoggedIn) {
                    mainIntent = new Intent(MainActivity.this, LoginPage.class);
                }else{
                    mainIntent = new Intent(MainActivity.this, WelcomeScreen.class);
                    mainIntent.putExtra("userName",prefs.getString("userName","NEWUSER"));
                }
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 1500);


    }
}