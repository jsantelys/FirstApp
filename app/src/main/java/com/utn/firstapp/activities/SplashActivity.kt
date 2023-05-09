package com.utn.firstapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.utn.firstapp.R

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        , splashTimeOut)
    }
}