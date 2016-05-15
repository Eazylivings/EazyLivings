package eazylivings.com.eazylivings;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.database.LocalDatabaseHandler;
import eazylivings.com.eazylivings.database.SignUpDetails;

public class RegisterNewUser extends AppCompatActivity {


    LocalDatabaseHandler localDatabaseHandler;

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



       // boolean checkEmail=validateEmailAddress(emailAddress.getText().toString());
        //boolean checkPassword=checkPassWordAndConfirmPassword(password.getText().toString(),confirmPassword.getText().toString());

        //if(emailAddress.getText().toString()==null && !checkEmail && !checkPassword){



        //}else{

            localDatabaseHandler=new LocalDatabaseHandler(this,Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);

            ContentValues values=new ContentValues();
            values.put(Constants.COLUMN_USERNAME,userName.getText().toString());
            values.put(Constants.COLUMN_EMAIL_ADDRESS,emailAddress.getText().toString());
            values.put(Constants.COLUMN_PASSWORD,password.getText().toString());
            values.put(Constants.COLUMN_SALT,"");

            localDatabaseHandler.insertNewRecordIntoTable(Constants.SIGNUP_DETAILS_TABLE,values);

            localDatabaseHandler.fetchDetailsFromTable();

//        }





    }

    public boolean checkPassWordAndConfirmPassword(String password,String confirmPassword)
    {
        boolean passwordStatus = false;
        if (confirmPassword != null && password != null)
        {
            if (password.equals(confirmPassword))
            {
                passwordStatus = true;
            }
        }
        return passwordStatus;
    }

    private boolean validateEmailAddress(String emailAddress){

        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
    }

}
