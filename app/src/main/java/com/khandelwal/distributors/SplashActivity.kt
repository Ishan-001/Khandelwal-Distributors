package com.khandelwal.distributors

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({

            YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.google_sign_in_btn));

            YoYo.with(Techniques.SlideInLeft)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.message1));

            YoYo.with(Techniques.SlideInRight)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.message2));

        }, 500)

    }
}