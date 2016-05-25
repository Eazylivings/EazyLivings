package eazylivings.com.eazylivings.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import eazylivings.com.eazylivings.R;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        final ImageView imageViewFirst = (ImageView) findViewById(R.id.aboutUs_imageView_shwetang);
        imageViewFirst.requestLayout();




        ImageView imageViewSecond = (ImageView) findViewById(R.id.aboutUs_imageView_vivek);
      /*  if(imageViewFirst!=null){
        imageViewFirst.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                imageViewFirst.getLayoutParams().height = 70;
                imageViewFirst.setLayoutParams(layoutParams);

                return true;
            }
        });
    }*/
    }
}

