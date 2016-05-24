package eazylivings.com.eazylivings.validators;

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
            return password.getText().toString().matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$");
        }else{
            return false;
        }
    }

    public static boolean checkEmailFormat(EditText emailAddress){

        if(emailAddress!=null) {

            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress.getText().toString()).matches();
        }else {
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

    public static boolean checkExistingUser(EditText userName, Context context){

        ServerDatabaseHandler serverDatabaseHandler=new ServerDatabaseHandler(context);
        if(userName!=null){
            return serverDatabaseHandler.getExistingUser(userName.getText().toString());
        }else
        {
            return false;
        }
    }

    public static boolean checkContactNumber(EditText contactNumber){

        if(contactNumber!=null){
            if(contactNumber.getText().toString().matches("\\+?\\d[\\d -]{8,12}\\d")){
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
