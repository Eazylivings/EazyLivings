package eazylivings.com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.VO.UserDetails;
import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.database.ServerDatabaseHandler;
import eazylivings.com.eazylivings.firsttimeinstallation.DBCreation;
import eazylivings.com.eazylivings.validators.ValidateInputs;

public class RegisterNewUser extends AppCompatActivity {

    DBCreation dbCreation;
    AlertDialog alertDialog;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);
        backButton = (Button)findViewById(R.id.newUser_button_backToLogin);
        backButtonListener(backButton);

    }

    public void onClickRegisterNewUser(View view) {

        final EditText userName = (EditText) findViewById(R.id.newUser_text_userName);
        final EditText emailAddress = (EditText) findViewById(R.id.newUser_text_email);
        final EditText password = (EditText) findViewById(R.id.newUser_text_password);
        final EditText contactNumber = (EditText) findViewById(R.id.newUser_text_contactNumber);

        /*if (userName != null){
            userName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        if(!ValidateInputs.checkUsernameFormat(userName)){
                            generatePopupMessages("Please check username.");
                        }
                    }
                }
            });
    }
        if (emailAddress != null){
            emailAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        if(!ValidateInputs.checkEmailFormat(emailAddress)){
                            generatePopupMessages("Please provide correct email address.");
                        }
                    }
                }
            });
        }
        if (contactNumber != null){
            contactNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        if(!ValidateInputs.checkPasswordFormat(contactNumber)){
                            generatePopupMessages("Please provide correct password.");
                        }
                    }
                }
            });
        }
        if (password != null){
            password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        if(!ValidateInputs.checkPasswordFormat(password)){
                            generatePopupMessages("Please provide correct password.");
                        }
                    }
                }
            });
        }*/

        boolean isEmailFormatCorrect=ValidateInputs.checkEmailFormat(emailAddress);
        boolean isUserAlreadyPresent=ValidateInputs.checkExistingUser(userName,getApplicationContext());
        boolean isUserNameFormatCorrect=ValidateInputs.checkUsernameFormat(userName);
        boolean isPasswordFormatCorrect=ValidateInputs.checkPasswordFormat(password);

        //if(userName!=null && password!=null && emailAddress!=null && contactNumber!=null) {

            ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(getBaseContext());
            serverDatabaseHandler.execute("register", "gfdgfdg", "dgfdgd", "dgdfgfd","dgfdgfdg");

       // }

        if(isUserNameFormatCorrect && isEmailFormatCorrect && !isUserAlreadyPresent && isPasswordFormatCorrect){

            UserDetails userDetails=new UserDetails();
            dbCreation=new DBCreation(this,Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);

            userDetails.setFirst_name("");
            userDetails.setEmail_address("");
            userDetails.setContact_number("");
            userDetails.setPassword("");

            dbCreation.insertServicesIntoTable();
            dbCreation.insertUserDetails(userDetails);
            dbCreation.createUserSpecificTables(userName.getText().toString());
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            prefs.edit().putBoolean("loginStatus", true).apply();
            prefs.edit().putString("userName", userName.getText().toString()).apply();


        }
    }

    private void generatePopupMessages(String message){
        {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterNewUser.this).create();
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
    private void backButtonListener(Button backButton){

        if (backButton != null) {
            backButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }

}
