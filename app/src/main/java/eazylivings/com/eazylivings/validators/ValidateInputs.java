package eazylivings.com.eazylivings.validators;

import android.widget.EditText;

import eazylivings.com.eazylivings.database.ServerDatabaseHandler;

/**
 * Created by shweagar on 5/18/2016.
 */
public class ValidateInputs {

    public static boolean checkPassWordAndConfirmPassword(EditText password, EditText confirmPassword)
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

    public static boolean checkPassword(EditText password){

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


    public static boolean validateEmailAddress(EditText emailAddress){

        if(emailAddress!=null) {

            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches();
        }else {
            return false;
        }
    }

    public static boolean checkUsername(EditText userName){

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

    public static boolean checkExistingUser(EditText userName){

        ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler();
        if(userName!=null){
            return serverDatabaseHandler.getExistingUser(userName.getText().toString());
        }else
        {
            return false;
        }
    }


    public static boolean checkExistingEmail(EditText emailAddress){

        if(emailAddress!=null){

            return true;

        }else{
            return false;
        }

    }
}
