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

        boolean isEmailCorrect=validateEmailAddress(emailAddress);
        boolean isConfirmPasswordCorrect=checkPassWordAndConfirmPassword(password,confirmPassword);
        boolean isUserAlreadyPresent=checkExistingUser(userName);
        boolean isUserNameCorrect=checkUsername(userName);
        boolean isPasswordCorrect=checkPassword(password);

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
    private boolean checkPassWordAndConfirmPassword(EditText password,EditText confirmPassword)
    {

        if (confirmPassword != null && password != null)
        {
            if (password.equals(confirmPassword))
            {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    private boolean checkPassword(EditText password){

        if(password!=null){
            if(password.getText().toString().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }


    private boolean validateEmailAddress(EditText emailAddress){

        if(emailAddress!=null) {

            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches();
        }else {
            return false;
        }
    }

    private boolean checkUsername(EditText userName){

        if(userName!=null ){

            if(userName.getText().toString().matches("^[a-z0-9_-]{3,15}$")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    private boolean checkExistingUser(EditText userName){

        ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler();
        if(userName!=null){
            return serverDatabaseHandler.getExistingUser(userName.getText().toString());
        }else
        {
            return false;
        }
    }


    private void generatePopupMessages(String message){

    }
}
