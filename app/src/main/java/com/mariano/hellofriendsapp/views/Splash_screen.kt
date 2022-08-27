package com.mariano.hellofriendsapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.mariano.hellofriendsapp.App
import com.mariano.hellofriendsapp.R

@Suppress("DEPRECATION")
class Splash_screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val backgroundImage: ImageView = findViewById(R.id.splash_image)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        backgroundImage.startAnimation(slideAnimation)

        val app = App.getInstance()
        val token = app.getSharedPrefs().getString("ACCESS_TOKEN", null)

        if (token == null)
        {
            Handler().postDelayed({
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
        else
        {
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 3000)
        }
    }
}