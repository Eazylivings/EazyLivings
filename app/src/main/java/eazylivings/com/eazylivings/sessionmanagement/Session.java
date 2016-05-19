package eazylivings.com.eazylivings.sessionmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    static SharedPreferences prefs;

    public static void setLoginStatus(boolean isUserLoggedIn,Context context){

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putBoolean("loginStatus",isUserLoggedIn).commit();
    }
    public static void setLogUserName(String userName,Context context){

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("userName",userName).commit();
    }

    public static boolean getLoginStatus(Context context){

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean isUserLoggedIn=prefs.getBoolean("loginStatus",false);

        return isUserLoggedIn;

    }

    public static String getusername(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String userName=prefs.getString("userName","NEWUSER");

        return userName;


    }
}
