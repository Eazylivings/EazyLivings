package eazylivings.com.eazylivings.activities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.database.LocalDatabaseHandler;
import eazylivings.com.eazylivings.database.SignUpDetails;
import eazylivings.com.eazylivings.firsttimeinstallation.DBCreation;

public class RegisterNewUser extends AppCompatActivity {


    LocalDatabaseHandler localDatabaseHandler;
    DBCreation dbCreation;
    private String userName=((EditText)findViewById(R.id.newUser_text_userName)).getText().toString();
    private String emailAddress=((EditText)findViewById(R.id.newUser_text_email)).getText().toString();
    private String password=((EditText)findViewById(R.id.newUser_text_password)).getText().toString();
    private String confirmPassword=((EditText)findViewById(R.id.newUser_text_confirmPassword)).getText().toString();

    private boolean isInformationValidated=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);
    }

    public void registerNewUser(View view){


        boolean checkEmail=validateEmailAddress(emailAddress);
        boolean checkPassword=checkPassWordAndConfirmPassword(password,confirmPassword);

        if(isInformationValidated){
            dbCreation=new DBCreation(this,Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);
            ContentValues values=new ContentValues();
            values.put(Constants.COLUMN_USERNAME,userName);
            values.put(Constants.COLUMN_EMAIL_ADDRESS,emailAddress);
            values.put(Constants.COLUMN_PASSWORD,password);

            dbCreation.insertNewRecordIntoTable(Constants.USER_DETAILS_TABLE_CREATION_QUERY,values);

        }else{



        }





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

    public static String getNextSalt() {
        return null;
    }

}
