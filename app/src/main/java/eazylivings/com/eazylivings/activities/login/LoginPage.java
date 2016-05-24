package eazylivings.com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.activities.WelcomeScreen;
import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.database.ServerDatabaseHandler;
import eazylivings.com.eazylivings.firsttimeinstallation.DBCreation;
import eazylivings.com.eazylivings.validators.ValidateInputs;

public class LoginPage extends AppCompatActivity {

    private String userName;
    DBCreation dbCreation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void onClickLoginButton(View view) {
        EditText editText_userName = (EditText) findViewById(R.id.loginPage_editText_userName);
        EditText editText_password = (EditText) findViewById(R.id.loginPage_editText_password);

        if(editText_userName!=null && editText_password!=null) {
            if (editText_userName.getText().toString().equalsIgnoreCase("")) {
                generatePopupMessage("Please enter username");
            } else if (editText_password.getText().toString().equalsIgnoreCase("")) {
                generatePopupMessage("Please enter password");
            } else {

                boolean isUserOnline = ValidateInputs.isInternetAvailable(getApplicationContext());
                if (isUserOnline) {
                    ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(getApplicationContext());
                    serverDatabaseHandler.execute(Constants.LOGIN,editText_userName.getText().toString(),editText_password.getText().toString());
                }
            }
        }
    }

    public void onClickRegisterButton(View view) {

        if(ValidateInputs.isInternetAvailable(getApplicationContext())) {

            Intent intent = new Intent(this, RegisterNewUser.class);
            startActivity(intent);
        }else{
            generatePopupMessage("Please check internet connection.");
        }
    }

    public void onClickForgotPassword(View view) {

        if(ValidateInputs.isInternetAvailable(getApplicationContext())) {

            Intent intent = new Intent(this, ForgotPassword.class);
            startActivity(intent);
        }else{
            generatePopupMessage("Please check internet connection.");
        }
    }

    private void setupUserProfile(){

        boolean isUserSpecificTablesPresent=dbCreation.checkIfUserSpecificTableExists(userName);

        if(isUserSpecificTablesPresent){
            setSession();

        }else{
            dbCreation.createUserSpecificTables(userName);
            dbCreation.populateUerSpecificTables(userName);
            setSession();
        }
    }

    private void setSession(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().putBoolean("loginStatus", true).apply();
        prefs.edit().putString("userName", userName).apply();
    }

    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(LoginPage.this).create();
        alertDialog.setTitle("Alert");
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
