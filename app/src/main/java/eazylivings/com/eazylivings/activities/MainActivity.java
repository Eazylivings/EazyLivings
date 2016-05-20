package eazylivings.com.eazylivings.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.database.ServerDatabaseHandler;
import eazylivings.com.eazylivings.sessionmanagement.Session;

public class MainActivity extends Activity {

    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username=(EditText)findViewById(R.id.loginPage_editText_userName);
        password=(EditText)findViewById(R.id.loginPage_editText_password);
        final boolean isUserLoggedIn= Session.getLoginStatus(getApplicationContext());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent;
                if(!isUserLoggedIn) {
                    mainIntent = new Intent(MainActivity.this, LoginPage.class);
                }else{
                    mainIntent = new Intent(MainActivity.this, WelcomeScreen.class);
                    mainIntent.putExtra("userName",Session.getusername(getApplicationContext()));
                }
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 2500);


    }

    public void OnLoginForOnlineDataBase(View view){
        String userName=username.getText().toString();
        String passWord=password.getText().toString();
        String type="login";
        ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(this);
        serverDatabaseHandler.execute(type,userName,passWord);
    }
}