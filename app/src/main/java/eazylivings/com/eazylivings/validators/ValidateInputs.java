package eazylivings.com.eazylivings.validators;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;

import java.net.InetAddress;

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

    public static boolean checkPasswordForamt(EditText password){

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


    public static boolean checEmailFormat(EditText emailAddress){

        if(emailAddress!=null) {

            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches();
        }else {
            return false;
        }
    }

    public static boolean checkUsernameFormat(EditText userName){

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

    public static boolean checkExistingUser(EditText userName, Context context){

        ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(context);
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

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com"); //You can replace it with your name

            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConnectedToWiFi(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return  activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean checkLogInDetails(EditText userName, EditText password,Context context){

        if(userName.getText().toString().equalsIgnoreCase("shwetang") && password.getText().toString().equalsIgnoreCase("password")){
            return true;
        }else{
            return false;
        }
    }
}
