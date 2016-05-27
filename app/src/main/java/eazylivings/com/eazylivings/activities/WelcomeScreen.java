package eazylivings.com.eazylivings.activities;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.activities.login.LoginPage;
import eazylivings.com.eazylivings.sharedpreference.SharedPreference;

public class WelcomeScreen extends AppCompatActivity {

    String welcomeText="Welcome ";
    String userName;
    SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        userName = prefs.getString("userName", "Newbie.");
        setTitle("Welcome "+ userName + "!!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isUserLoggedIn = prefs.getBoolean("loginStatus",false);

        if(isUserLoggedIn) {
            inflater.inflate(R.menu.items_logout, menu);
        }else{
            inflater.inflate(R.menu.items_login, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed(){
        finish();
        System.exit(0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        super.onOptionsItemSelected(item);
        sharedPreference=new SharedPreference();

        switch(item.getItemId()){
            case R.id.userProfile:

                if(sharedPreference.getBooleanValueFromSharedPreference(getApplicationContext(),"loginStatus")){
                    intent = new Intent(this, UserProfile.class);
                    startActivity(intent);
                }else{
                    generatePopupMessage("Please login to see your porfile");
                }

                break;

            case R.id.logOut:
                generatePopupMessage("Successfully Logged Out..");
                intent = new Intent(this, LoginPage.class);
                startActivity(intent);
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),"loginStatus");
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),"userName");
                break;
            case R.id.logIn:
                intent = new Intent(this, LoginPage.class);
                startActivity(intent);
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),"loginStatus");
                sharedPreference.removeValueFromSharedPreference(getApplicationContext(),"userName");
                break;
            case R.id.preferences:
                intent = new Intent(this, LoginPage.class);
                startActivity(intent);
                sharedPreference.setBooleanValueInSharedPreference(getApplicationContext(),"loginStatus", false);
                sharedPreference.setStringValueInSharedPreference(getApplicationContext(),"username","Newbie.");
                break;
        }
        return true;
   }

    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create(); //Use context
        alertDialog.setTitle("Warning");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
