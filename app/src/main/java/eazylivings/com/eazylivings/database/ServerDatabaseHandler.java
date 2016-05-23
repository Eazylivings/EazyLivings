package eazylivings.com.eazylivings.database;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;

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

import eazylivings.com.eazylivings.sharedpreference.SharedPreference;

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
                result="";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }

                bufferedReader.close();
                inputStream.close();
                httpUrlConnection.disconnect();
                setSharedPreferences(result);
                Log.i("12","Server Result "+result);
                return result;
            } catch (MalformedURLException e) {
                result="Exception Occurred";

            } catch (IOException e) {
                result="Exception Occurred";
            }catch(Exception e){
                result="Exception Occurred";
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
                    result="Exception Occurred";
                } catch (IOException e) {
                    result="Exception Occurred";
                }catch (Exception e){
                    result="Exception Occurred";
                }
            }
        }
        return result;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onPostExecute(String result) {
        SharedPreference sharedPreference=new SharedPreference();
        Log.i("12","Result in post execute - "+sharedPreference.getStringValueFromSharedPreference(context,"result"));
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


        SharedPreference sharedPreference=new SharedPreference();
        sharedPreference.setStringValueInSharedPreference(context,"result",result);
        Log.i("12","Result setted into preference "+result);

        String testString=sharedPreference.getStringValueFromSharedPreference(context,"result");
        Log.i("12","Result into the preference during setting "+testString);
    }
}

