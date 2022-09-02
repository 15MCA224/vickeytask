package com.best.vignesh.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.best.vignesh.R
import kotlinx.coroutines.*

class SplashScreen : AppCompatActivity() {
    lateinit var activityScope:CoroutineScope
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val activityScope = CoroutineScope(Dispatchers.Main)

        activityScope.launch {
            delay(3000)

            val intent = Intent(this@SplashScreen, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }



}