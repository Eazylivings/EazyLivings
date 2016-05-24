package eazylivings.com.eazylivings.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import eazylivings.com.eazylivings.VO.UserDetails;
import eazylivings.com.eazylivings.activities.WelcomeScreen;
import eazylivings.com.eazylivings.constants.Constants;
import eazylivings.com.eazylivings.firsttimeinstallation.DBCreation;
import eazylivings.com.eazylivings.sharedpreference.SharedPreference;

public class ServerDatabaseHandler extends AsyncTask<String,Void,String>  {

    Context context;
    static String result="";
    static String currentAction="";
    String userName="";
    UserDetails userDetails=new UserDetails();
    Activity activity;

    public ServerDatabaseHandler(Context ctx,Activity baseActivity){
        context=ctx;
        this.activity=baseActivity;
    }

    @Override
    protected String doInBackground(String... params) {

        currentAction = params[0];

        String post_data="";

            try {
                URL url=new URL(Constants.LOGIN_URL);
                userName=params[1];
                if(currentAction.equalsIgnoreCase(Constants.LOGIN)){
                    url = new URL(Constants.LOGIN_URL);
                    post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8");

                }else if(currentAction.equalsIgnoreCase(Constants.REGISTER)){
                    url = new URL(Constants.REGISTER_URL);
                    post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8")+"&"
                            + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8")+"&"
                            + URLEncoder.encode("phoneNo", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8");


                    userDetails.setUserName(params[1]);
                    userDetails.setPassword(params[2]);
                    userDetails.setEmail_address(params[3]);
                    userDetails.setContact_number(params[4]);
                }

                HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setRequestMethod("POST");
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setDoInput(true);
                OutputStream outputStream = httpUrlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));


                if(bufferedWriter!=null) {
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }

                if(outputStream!=null) {
                    outputStream.close();
                }
                InputStream inputStream = httpUrlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line = "";
                result="";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpUrlConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                result="Exception Occurred";
                return result;
            } catch (IOException e) {
                result="Exception Occurred";
                return result;
            }catch(Exception e){
                result="Exception Occurred";
                return result;
            }
        }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String accountAuthenticationString) {

        if (accountAuthenticationString.equalsIgnoreCase("Login Success")) {

            setSharedPreferences("userName",userName);
            Intent intent = new Intent(context,WelcomeScreen.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        } else if(accountAuthenticationString.equalsIgnoreCase("Login Failed")) {
            generatePopupMessage("Please check login details and try again");

        }else if(result.equalsIgnoreCase("Registration Success")){
            DBCreation dbCreation;
            dbCreation=new DBCreation(context,Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);
            dbCreation.insertServicesIntoTable();
            dbCreation.createUserSpecificTables(userName);
            dbCreation.insertUserDetails(userDetails);

            setSharedPreferences("userName",userName);

        }else if(result.equalsIgnoreCase("Registration Failed")){
            generatePopupMessage("Failed to register. Please try again with correct inputs");
        }else{
            generatePopupMessage("Some error occurred. Please try again after sometime");
        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public boolean getExistingUser(String userName){
        boolean isUserPresent=false;

        return isUserPresent;
    }

    private void setSharedPreferences(String key,String value){


        SharedPreference sharedPreference=new SharedPreference();
        sharedPreference.setStringValueInSharedPreference(context,key,value);
        sharedPreference.setBooleanValueInSharedPreference(context,"loginStatus",true);
    }

    private void generatePopupMessage(String message){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create(); //Use context
        alertDialog.setTitle("Warning");
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

