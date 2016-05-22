package eazylivings.com.eazylivings.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.activities.login.LoginPage;
import eazylivings.com.eazylivings.sessionmanagement.Session;

public class WelcomeScreen extends AppCompatActivity {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.phone:
                Toast.makeText(getBaseContext(), "You selected Phone", Toast.LENGTH_SHORT).show();
                break;

            case R.id.computer:
                Toast.makeText(getBaseContext(), "You selected Computer", Toast.LENGTH_SHORT).show();
                break;

            case R.id.gamepad:
                Toast.makeText(getBaseContext(), "You selected Gamepad", Toast.LENGTH_SHORT).show();
                break;

            case R.id.camera:
                Toast.makeText(getBaseContext(), "You selected Camera", Toast.LENGTH_SHORT).show();
                break;

            case R.id.video:
                Toast.makeText(getBaseContext(), "You selected Video", Toast.LENGTH_SHORT).show();
                break;

            case R.id.email:
                Toast.makeText(getBaseContext(), "You selected EMail", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;

    }
}
