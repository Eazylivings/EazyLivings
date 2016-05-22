package eazylivings.com.eazylivings.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.activities.login.LoginPage;
import eazylivings.com.eazylivings.sessionmanagement.Session;

public class WelcomeScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Intent intent = getIntent();
        TextView text=(TextView)findViewById(R.id.welcomeScreen_text_welcome);
        text.setText("Welcome "+Session.getusername(getApplicationContext()));
    }

    public void onClickLogout(View view){

        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
        Session.setLoginStatus(false,getApplicationContext());
    }
    public void onClickUserProfile(View view){
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);

    }
}
