package eazylivings.com.eazylivings.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.activities.login.LoginPage;

public class WelcomeScreen extends AppCompatActivity {

    String welcomeText="Welcome ";
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Log.i("12","15");
        TextView text=(TextView)findViewById(R.id.welcomeScreen_text_welcome);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(prefs!=null) {
            userName = prefs.getString("userName", "NEWUSER");
        }
        if(text!=null) {
            welcomeText=welcomeText+userName;
            text.setText(welcomeText);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.userProfile:
                intent = new Intent(this, UserProfile.class);
                startActivity(intent);
                break;

            case R.id.logOut:
                intent = new Intent(this, LoginPage.class);
                startActivity(intent);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                prefs.edit().putBoolean("loginStatus", false).apply();
                break;
        }
        return true;

    }
}
