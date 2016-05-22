package eazylivings.com.eazylivings.activities;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import eazylivings.com.eazylivings.R;

public class Introduction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        ArrayList<String> listOfContent=new ArrayList<String>();
        listOfContent.add("zero");
        listOfContent.add("one");
        listOfContent.add("One Platform");
        listOfContent.add("two");
        listOfContent.add("three");
        listOfContent.add("Problems");
        listOfContent.add("@four");
        listOfContent.add("five");
        listOfContent.add("Solutions");



        ImageView leftImage=(ImageView)findViewById(R.id.introduction_imageView_leftNumber);
        ImageView rightImage=(ImageView)findViewById(R.id.introduction_imageView_rightNumber);
        TextView message=(TextView)findViewById(R.id.introduction_text_message);

        Resources r = getResources();
        int picId;

        for(int i=0;i<listOfContent.size();i++){


            picId = r.getIdentifier(listOfContent.get(i), "drawable", "drawable");
            leftImage.setBackgroundResource(picId);

            picId= r.getIdentifier(listOfContent.get(++i), "drawable", "drawable");
            rightImage.setBackgroundResource(picId);

            message.setText(listOfContent.get(++i));

           delay();
        }
    }


    private void delay(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 1000);


    }
}
