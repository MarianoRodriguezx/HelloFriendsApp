package com.mariano.hellofriendsapp.views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputEditText
import com.mariano.hellofriendsapp.R
import com.mariano.hellofriendsapp.databinding.ActivityMainBinding
import com.mariano.hellofriendsapp.network.viewModels.UserViewModel
import com.mariano.hellofriendsapp.utils.models.TokenResponse
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@AndroidEntryPoint
class Login : AppCompatActivity() {

    private val userViewModel by viewModels<UserViewModel>()
    //editor.putString("email", email)
    //editor.commit()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)

        val btn = findViewById<Button>(R.id.login)
        val email = findViewById<TextInputEditText>(R.id.email)
        val password = findViewById<TextInputEditText>(R.id.password)

        btn.setOnClickListener {
            prueba(email.text.toString(), password.text.toString())
        }
    }
    private fun prueba(email: String, password: String)
    {
            userViewModel.login(
                email, password
            ) { e ->
                SetToken(e)
            }

    }
    private fun SetToken(response: TokenResponse)
    {
        var settings: SharedPreferences = getSharedPreferences("HelloFriendsAppData", Context.MODE_PRIVATE)
        var editor = settings.edit()
        editor.putString("ACCESS_TOKEN", response.token)
        editor.apply()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}