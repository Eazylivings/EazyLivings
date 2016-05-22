package eazylivings.com.eazylivings.sessionmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    static SharedPreferences prefs;

    public static void setLoginStatus(boolean isUserLoggedIn,Context context){

        if(prefs!=null) {
            prefs.edit().remove("loginStatus");
            prefs.edit().commit();
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit().putBoolean("loginStatus", isUserLoggedIn).commit();
        }
    }
    public static void setLogUserName(String userName,Context context){

        if(prefs!=null) {
            prefs.edit().remove("userName");
            prefs.edit().commit();
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
            prefs.edit().putString("userName", userName).commit();
        }
    }

    public static void setActivityResult(String result,Context context){

        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit().putString("result",result).commit();
    }

    public static boolean getLoginStatus(Context context){

        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getBoolean("loginStatus",false);
    }

    public static String getusername(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getString("userName","NEWUSER");
    }
    public static String getActivityResult(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getString("result","");
    }
}
