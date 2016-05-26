package eazylivings.com.eazylivings.validators;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;

import eazylivings.com.eazylivings.VO.UserDetails;
import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.database.ServerDatabaseHandler;
import eazylivings.com.eazylivings.sharedpreference.SharedPreference;


public class ValidateInputs {


    public static boolean checkPasswordFormat(EditText password){

        if(password!=null){
            return password.getText().toString().matches("^[a-zA-Z0-9@$_]{3,15}$");
        }else{
            return false;
        }
    }

    public static boolean checkEmailFormat(EditText emailAddress){

        if(emailAddress!=null){
            return emailAddress.getText().toString().matches("^[a-zA-Z0-9@$_]+[@][a-zA-Z]{2,8}[.][a-zA-Z]{2,3}$");
        }else{
            return false;
        }
    }

    public static boolean checkUsernameFormat(EditText userName){

        if(userName!=null ){

            if(userName.getText().toString().matches("^[a-zA-Z]{3,15}$")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean checkExistingUser(EditText userName, Context context, Activity activity){

        ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(context,activity);
        if(userName!=null){
            return serverDatabaseHandler.getExistingUser(userName.getText().toString());
        }else
        {
            return false;
        }
    }

    public static boolean checkContactNumber(EditText contactNumber){

        if(contactNumber!=null){
            if(contactNumber.getText().toString().matches("^[789][0-9]{10}+$")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public static boolean isInternetAvailable(Context context) {

        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }

    public boolean isConnectedToWiFi(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return  activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;
    }
}
