package eazylivings.com.eazylivings.activities.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.activities.WelcomeScreen;
import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.firsttimeinstallation.DBCreation;
import eazylivings.com.eazylivings.sessionmanagement.Session;
import eazylivings.com.eazylivings.validators.ValidateInputs;

public class LoginPage extends AppCompatActivity {

    private String userName;
    private String password;
    DBCreation dbCreation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void onClickLoginButton(View view) {
        EditText editText_userName = (EditText) findViewById(R.id.loginPage_editText_userName);
        EditText editText_password = (EditText) findViewById(R.id.loginPage_editText_password);

        if(editText_userName!=null && editText_password!=null){

            boolean isAccountAuthenticated= ValidateInputs.checkLogInDetails(editText_userName,editText_password,getApplicationContext());
            userName=editText_userName.getText().toString();
            if(isAccountAuthenticated){

                setupUserProfile();
                //setSession();
                Intent intent = new Intent(this, WelcomeScreen.class);
                startActivity(intent);

            }else
            {
                //popups
            }
        }

    }

    public void onClickRegisterButton(View view) {

        Intent intent = new Intent(this, RegisterNewUser.class);
        startActivity(intent);
    }


    public void onClickForgotPassword(View view) {

        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
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
        Session.setLogUserName(userName,getApplicationContext());
        Session.setLoginStatus(true,getApplicationContext());
    }

    private void updateLoggedUsersTable(){

        dbCreation=new DBCreation(getApplicationContext(), Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);


    }
}
