package eazylivings.com.eazylivings;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity {


    String tag="12";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

    }

    public void onClickLoginButton(View view) {
        EditText userName = (EditText) findViewById(R.id.loginPage_editText_userName);
        EditText password = (EditText) findViewById(R.id.loginPage_editText_password);


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
