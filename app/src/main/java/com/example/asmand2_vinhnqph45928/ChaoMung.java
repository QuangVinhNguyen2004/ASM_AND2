package com.example.asmand2_vinhnqph45928;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ChaoMung extends AppCompatActivity {
    private static final long SPLASH_DELAY = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chao_mung);
        ImageView anh=findViewById(R.id.fptchaomung);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(ChaoMung.this, Login.class);
                startActivity(intent);

                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, SPLASH_DELAY);
        // Tạo hiệu ứng làm mờ
        Animation fadeOutAnimation = new AlphaAnimation(1.0f, 0.3f);
        fadeOutAnimation.setDuration(2000);
        anh.startAnimation(fadeOutAnimation);
    }
    }

