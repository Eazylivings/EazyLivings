package eazylivings.com.eazylivings.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import eazylivings.com.eazylivings.R;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Intent intent = getIntent();
        TextView text=(TextView)findViewById(R.id.welcomeScreen_text_welcome);
        text.setText("Welcome "+intent.getStringExtra("userName"));



    }
}
