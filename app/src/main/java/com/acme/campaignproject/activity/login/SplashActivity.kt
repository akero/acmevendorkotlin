package com.acme.campaignproject.activity.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.acme.campaignproject.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this, WelcomeActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)

    }
}