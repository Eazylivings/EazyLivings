package eazylivings.com.eazylivings.activities.login;


import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import eazylivings.com.eazylivings.R;
import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.database.ServerDatabaseHandler;

public class ForgotPassword extends AppCompatActivity {

    Button backButton;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backButton = (Button)findViewById(R.id.forgotPassword_button_backToLogin);
        backButtonListener(backButton);
    }

    public void onClickRetrievePassword(View view){

        EditText emailAddress=(EditText)findViewById(R.id.forgotPassword_button_emailAddress);
        Button retrievePassword=(Button)findViewById(R.id.forgotPassword_button_retrievePassword);
        TextView defaultMessage=(TextView)findViewById(R.id.forgotPassword_button_defaultMessage) ;
        boolean isEmailSuccessfullySent=false;


        if(emailAddress!=null){
            isEmailSuccessfullySent=sendEmailForPasswordRetrieval(emailAddress);
        }else{
            generatePopupMessages("Please check email Address.");
        }
        if(emailAddress!=null && retrievePassword!=null && defaultMessage!=null) {

            emailAddress.setVisibility(View.GONE);
            retrievePassword.setVisibility(View.GONE);
        }
        if(isEmailSuccessfullySent && defaultMessage!=null){
            defaultMessage.setText(Constants.MESSAGE_FOR_SUCCESSFUL_RESET_PASSWORD);
        }else if(defaultMessage!=null){
            defaultMessage.setText(Constants.MESSAGE_FAIL_RESET_PASSWORD);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.forgotPassword_button_retrievePassword);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        if(backButton!=null){
            backButton.setLayoutParams(layoutParams);
            backButtonListener(backButton);
        }

    }

    private boolean sendEmailForPasswordRetrieval(EditText emailAddress){

        ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(getApplicationContext());
        serverDatabaseHandler.execute(Constants.FORGOTPASSWORD,emailAddress.getText().toString());

        String activityResult="";

        return activityResult.equalsIgnoreCase("Email successfully sent");
    }

    private void generatePopupMessages(String message){

        {
            AlertDialog alertDialog = new AlertDialog.Builder(ForgotPassword.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(message);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

    }

    private void backButtonListener(Button backButton){

        if (backButton != null) {
            backButton.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }

}
