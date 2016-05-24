package eazylivings.com.eazylivings.activities.login;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
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
        final EditText password = (EditText) findViewById(R.id.newUser_text_password);
        final EditText emailAddress = (EditText) findViewById(R.id.newUser_text_email);
        final EditText contactNumber = (EditText) findViewById(R.id.newUser_text_contactNumber);

        boolean isEmailFormatCorrect=ValidateInputs.checkEmailFormat(emailAddress);
        boolean isUserAlreadyPresent=ValidateInputs.checkExistingUser(userName,getApplicationContext(),this);
        boolean isUserNameFormatCorrect=ValidateInputs.checkUsernameFormat(userName);
        boolean isPasswordFormatCorrect=ValidateInputs.checkPasswordFormat(password);
        boolean isContactNumberCorrect=ValidateInputs.checkContactNumber(contactNumber);

        if(isUserNameFormatCorrect && isEmailFormatCorrect && !isUserAlreadyPresent && isPasswordFormatCorrect && isContactNumberCorrect){


            UserDetails userDetails=new UserDetails();
            userDetails.setUserName(userName.getText().toString());
            userDetails.setPassword(password.getText().toString());
            userDetails.setEmail_address(emailAddress.getText().toString());
            userDetails.setContact_number(contactNumber.getText().toString());

            ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(getApplicationContext(),this);
            serverDatabaseHandler.execute(Constants.REGISTER,userName.getText().toString(),password.getText().toString(),emailAddress.getText().toString(),contactNumber.getText().toString());

        }else if(isUserAlreadyPresent){
            generatePopupMessages("This user is already present. Please sign in.");
        }else if(!isUserNameFormatCorrect){
            generatePopupMessages("Please check username");
        }else if(isEmailFormatCorrect){
            generatePopupMessages("Please provide correct email address");
        }else if(isPasswordFormatCorrect){
            generatePopupMessages("Please choose correct password");
        }else if(isContactNumberCorrect){
            generatePopupMessages("Please provide correct contact number.");
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
