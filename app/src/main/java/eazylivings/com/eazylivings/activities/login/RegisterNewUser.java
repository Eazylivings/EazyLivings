package eazylivings.com.eazylivings.activities.login;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.VO.UserDetails;
import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.database.ServerDatabaseHandler;
import eazylivings.com.eazylivings.firsttimeinstallation.DBCreation;
import eazylivings.com.eazylivings.sessionmanagement.Session;
import eazylivings.com.eazylivings.validators.ValidateInputs;

public class RegisterNewUser extends Activity {

    DBCreation dbCreation;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);
    }

    public void registerNewUser(View view){

        EditText userName=(EditText)findViewById(R.id.newUser_text_userName);
        EditText emailAddress=(EditText)findViewById(R.id.newUser_text_email);
        EditText password=(EditText)findViewById(R.id.newUser_text_password);
        EditText contactNo=(EditText)findViewById(R.id.newUser_text_contactNo);

        boolean isEmailFormatCorrect=ValidateInputs.checEmailFormat(emailAddress);
        boolean isUserAlreadyPresent=ValidateInputs.checkExistingUser(userName,getApplicationContext());
        boolean isUserNameFormatCorrect=ValidateInputs.checkUsernameFormat(userName);
        boolean isPasswordFormatCorrect=ValidateInputs.checkPasswordForamt(password);

        ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(getApplicationContext());
        if(userName!=null && password!=null && emailAddress!=null&&contactNo!=null) {
            Looper.prepare();
            serverDatabaseHandler.execute("register", userName.toString(), password.toString(), emailAddress.toString(), contactNo.toString());

        }





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
            Session.setLogUserName(userName.getText().toString(),getApplicationContext());
            Session.setLoginStatus(true,getApplicationContext());


        }else{

            if(!isUserAlreadyPresent){

                generatePopupMessages("This Username is already taken. Please choose different username.");

            }else if(!isUserNameFormatCorrect){

                generatePopupMessages("Please check username. It should contains Une number.");

            }else if(!isEmailFormatCorrect){

                generatePopupMessages("Please provide correct email address");

            }else if(!isPasswordFormatCorrect){

                generatePopupMessages("Password should adhere to password policies.");

            }else{
                generatePopupMessages("Confirm password should be same as password.");
            }
        }
    }

    private void generatePopupMessages(String message){
        alertDialog.setMessage(message);
    }
}
