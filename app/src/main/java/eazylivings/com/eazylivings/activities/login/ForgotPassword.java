package eazylivings.com.eazylivings.activities.login;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.validators.ValidateInputs;

public class ForgotPassword extends AppCompatActivity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backButton = (Button)findViewById(R.id.forgotPassword_button_backToLogin);
        if (backButton != null) {
            backButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public void onClickRetrievePassword(View view){

        EditText emailAddress=(EditText)findViewById(R.id.forgotPassword_button_emailAddress);
        Button retrievePassword=(Button)findViewById(R.id.forgotPassword_button_retrievePassword);
        TextView defaultMessage=(TextView)findViewById(R.id.forgotPassword_button_defaultMessage) ;
        boolean isEmailSuccessfullySent=false;


        /*if(emailAddress!=null){
            isEmailSuccessfullySent=sendEmailForPasswordretrieval(emailAddress);
        }else{
            generatePopupMessages("Please check email Address.");
        }*/

        if(isEmailSuccessfullySent ){

            if(emailAddress!=null && retrievePassword!=null && defaultMessage!=null) {
                emailAddress.setVisibility(View.GONE);
                retrievePassword.setVisibility(View.GONE);
                defaultMessage.setText("An Email has been sent to your registered email address.Please login with the One time password in the mails."+
                        " You can change the password by updating User Profile");
            }

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.BELOW, R.id.forgotPassword_button_retrievePassword);
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);


            if (backButton != null) {
                backButton.setLayoutParams(layoutParams);
                backButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }else{

            if(emailAddress!=null && retrievePassword!=null && defaultMessage!=null) {
                emailAddress.setVisibility(View.GONE);
                retrievePassword.setVisibility(View.GONE);
                defaultMessage.setText("An error Occurred while resetting your password. Please try again after sometime.");
            }

            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.BELOW, R.id.forgotPassword_button_retrievePassword);
            layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);


            if (backButton != null) {
                backButton.setLayoutParams(layoutParams);
                backButton.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }
    }

    private boolean sendEmailForPasswordretrieval(EditText emailAddress){

       return false;
    }

    private void generatePopupMessages(String message){

    }

}
