package com.example.mylogin;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;



import com.bumptech.glide.Glide;

public class ProfileImage extends Activity {
    Button btnImage;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView = findViewById(R.id.imageView);
        btnImage = findViewById(R.id.button);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getApplicationContext())
                        .load(R.drawable.flower)
                        .into(imageView);

            }
        });


    }


}
