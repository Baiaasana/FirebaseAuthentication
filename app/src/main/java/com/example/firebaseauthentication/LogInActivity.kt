package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauthentication.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listeners()
    }

    private fun listeners() {

        binding.imgLoginBackArrow.setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }

        binding.btnLoginLogin.setOnClickListener {
            val email = binding.edtLogMail.text.toString()
            val password = binding.edtLogPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_empty_field), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, getString(R.string.toast_log_in),
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this, ProfileActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this, getString(R.string.toast_incorect_values),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

        }
    }
}