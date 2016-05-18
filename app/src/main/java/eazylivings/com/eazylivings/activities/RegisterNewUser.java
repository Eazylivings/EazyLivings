package eazylivings.com.eazylivings.activities;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.database.ServerDatabaseHandler;
import eazylivings.com.eazylivings.firsttimeinstallation.DBCreation;
import eazylivings.com.eazylivings.validators.ValidateInputs;

public class RegisterNewUser extends AppCompatActivity {

    DBCreation dbCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);
    }

    public void registerNewUser(View view){

        EditText userName=(EditText)findViewById(R.id.newUser_text_userName);
        EditText emailAddress=(EditText)findViewById(R.id.newUser_text_email);
        EditText password=(EditText)findViewById(R.id.newUser_text_password);
        EditText confirmPassword=(EditText)findViewById(R.id.newUser_text_confirmPassword);

        boolean isEmailCorrect=ValidateInputs.validateEmailAddress(emailAddress);
        boolean isConfirmPasswordCorrect=ValidateInputs.checkPassWordAndConfirmPassword(password,confirmPassword);
        boolean isUserAlreadyPresent=ValidateInputs.checkExistingUser(userName);
        boolean isUserNameCorrect=ValidateInputs.checkUsername(userName);
        boolean isPasswordCorrect=ValidateInputs.checkPassword(password);

        if(isUserNameCorrect && isEmailCorrect && isConfirmPasswordCorrect && !isUserAlreadyPresent && isPasswordCorrect){

            dbCreation=new DBCreation(this,Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);
            ContentValues values=new ContentValues();
            values.put(Constants.COLUMN_USERNAME,userName.getText().toString());
            values.put(Constants.COLUMN_EMAIL_ADDRESS,emailAddress.getText().toString());
            values.put(Constants.COLUMN_PASSWORD,password.getText().toString());

            dbCreation.insertServicesIntoTable();
            dbCreation.insertUserDetails(values);


        }else{

            if(!isUserAlreadyPresent){

                generatePopupMessages("This Username is already taken. Please choose different username.");

            }else if(!isUserNameCorrect){

                generatePopupMessages("Please check username. It should contains Une number.");

            }else if(!isEmailCorrect){

                generatePopupMessages("Please provide correct email address");

            }else if(!isPasswordCorrect){

                generatePopupMessages("Password should adhere to password policies.");

            }else{
                generatePopupMessages("Confirm password should be same as password.");
            }
        }
    }



    private void generatePopupMessages(String message){

    }
}
