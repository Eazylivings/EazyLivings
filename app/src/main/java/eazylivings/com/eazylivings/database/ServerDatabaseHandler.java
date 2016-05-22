package eazylivings.com.eazylivings.database;

import android.app.AlertDialog;
import android.content.Context;
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

import eazylivings.com.eazylivings.sessionmanagement.Session;

public class ServerDatabaseHandler extends AsyncTask<String,Void,String>  {

    Context context;
    AlertDialog alertDialog;
    static String result="";

    public ServerDatabaseHandler(Context ctx){
        context=ctx;
    }

    @Override
    protected String doInBackground(String... params) {

        String type = params[0];
        String loginUrl = "http://eazylivings.com/login.php";
        if (type.equals("login")) {
            try {
                URL url = new URL(loginUrl);
                HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setRequestMethod("POST");
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setDoInput(true);
                OutputStream outputStream = httpUrlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String user_name = params[1];
                String password = params[2];
                String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpUrlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpUrlConnection.disconnect();

                setSharedPreferences(result);
                return result;
            } catch (MalformedURLException e) {
                String connectError="Please Check Network Connection";
                alertDialog.setMessage(connectError);
                alertDialog.show();
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }else if (type.equals("register")){
            {
                String registerUrl="http://eazylivings.com/register.php";
                try {
                    URL url = new URL(registerUrl);
                    HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                    httpUrlConnection.setRequestMethod("POST");
                    httpUrlConnection.setDoOutput(true);
                    httpUrlConnection.setDoInput(true);
                    OutputStream outputStream = httpUrlConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    String user_name = params[1];
                    String password = params[2];
                    String email=params[3];
                    String phoneNo=params[4];
                        String post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                                + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8")+"&"
                                + URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8")+"&"
                                + URLEncoder.encode("phoneNo", "UTF-8") + "=" + URLEncoder.encode(phoneNo, "UTF-8");
                        bufferedWriter.write(post_data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();

                        InputStream inputStream = httpUrlConnection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                        String line = "";

                        while ((line = bufferedReader.readLine()) != null) {
                            result += line;
                        }

                        bufferedReader.close();
                        inputStream.close();
                        httpUrlConnection.disconnect();

                    return result;
                } catch (MalformedURLException e) {
                    String connectError="Please Check Network Connection";
                    alertDialog.setMessage(connectError);
                    alertDialog.show();
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }
        }
        return result;
    }

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        //alertDialog.setMessage(result);
        //alertDialog.show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public boolean getExistingUser(String userName){
        boolean isUserPresent=false;

        return isUserPresent;
    }

    public String getPassword(String userName){
        String password="";

        return password;
    }

    private void setSharedPreferences(String result){


        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        if(preferences!=null) {
            preferences.edit().remove("result");
            preferences.edit().commit();
            preferences.edit().putString("result", result).commit();
        }

    }
}

