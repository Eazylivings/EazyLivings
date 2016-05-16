package eazylivings.com.eazylivings.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import eazylivings.com.eazylivings.R;

public class LoginPage extends AppCompatActivity {

    private String userName;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
    }

    public void onClickLoginButton(View view) {
        EditText editText_userName = (EditText) findViewById(R.id.loginPage_editText_userName);
        EditText editText_password = (EditText) findViewById(R.id.loginPage_editText_password);

        if(userName!=null && password!=null){

            userName=editText_userName.getText().toString();
            password=editText_password.getText().toString();

            if(userName.equalsIgnoreCase("shwetang") && password.equalsIgnoreCase("password")){

            }else
            {

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
}
