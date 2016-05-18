package eazylivings.com.eazylivings.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.validators.ValidateInputs;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button backButton = (Button)findViewById(R.id.forgotPassword_button_backToLogin);
        if (backButton != null) {
            backButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private void onClickRetrievePassword(View view){

        EditText emailAddress=(EditText)findViewById(R.id.forgotPassword_button_emailAddress);
        boolean isEmailPresentInRecords=ValidateInputs.checkExistingEmail(emailAddress);


        if(emailAddress!=null && isEmailPresentInRecords){
            sendEmail(emailAddress);
        }else{
            generatePopupMessages("Please check email Address.");
        }

        boolean isEmailSuccessfullySent=sendEmail(emailAddress);



    }

    private boolean sendEmail(EditText emailAddress){

       return false;
    }

    private void generatePopupMessages(String message){

    }

}
