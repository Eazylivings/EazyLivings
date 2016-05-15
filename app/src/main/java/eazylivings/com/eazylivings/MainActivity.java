package eazylivings.com.eazylivings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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