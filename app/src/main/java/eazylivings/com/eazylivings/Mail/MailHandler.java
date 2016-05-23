package eazylivings.com.eazylivings.Mail;

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
/**
 * Created by Vibek on 5/22/2016.
 */
public class MailHandler extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    static String result="";

    public MailHandler(Context ctx){
        context=ctx;
    }

    protected String doInBackground(String... params) {

        String type = params[0];
        String loginUrl = "http://eazylivings.com/mail.php";
        if(type.equals("send email"))
        {
        if (params[1].equals("forgotPassword")) {
            try {
                URL url = new URL(loginUrl);
                HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                httpUrlConnection.setRequestMethod("POST");
                httpUrlConnection.setDoOutput(true);
                httpUrlConnection.setDoInput(true);
                OutputStream outputStream = httpUrlConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String emailAddress = params[2];
                String post_data = URLEncoder.encode("emailAddress", "UTF-8") + "=" + URLEncoder.encode(emailAddress, "UTF-8");

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
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                preferences.edit().putString("result", result).commit();
                return result;
            } catch (MalformedURLException e) {
                String connectError = "Please Check Network Connection";
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


}
