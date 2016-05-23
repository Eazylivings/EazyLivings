package eazylivings.com.eazylivings.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SharedPreference {

    public void setStringValueInSharedPreference(Context context,String key,String value){

        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        if(preferences!=null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public String getStringValueFromSharedPreference(Context context,String key){
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        if(preferences!=null) {
            return preferences.getString(key, "empty");
        }else{
            return "";
        }
    }

    public void setBooleanValueInSharedPreference(Context context,String key,boolean value){

        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        if(preferences!=null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public boolean getBooleanValueFromSharedPreference(Context context,String key){
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        if(preferences!=null) {
            return preferences.getBoolean(key, false);
        }else{
            return false;
        }
    }



    public void removeValueFromSharedPreference(Context context,String valueToBeDeleted){
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(valueToBeDeleted);
        editor.commit();


    }
    public void clearSharedPreference(Context context){
        SharedPreferences preferences =  PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();

    }
}
