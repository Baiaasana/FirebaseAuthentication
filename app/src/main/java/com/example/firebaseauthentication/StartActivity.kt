package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseauthentication.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listeners()
    }

    private fun listeners() {

        binding.btnStartLogin.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
            finish()
        }
        binding.btnStartRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity1::class.java))
            finish()
        }
    }
}