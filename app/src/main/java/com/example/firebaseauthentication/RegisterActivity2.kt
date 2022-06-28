package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauthentication.databinding.ActivityRegister2Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityRegister2Binding

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("userInfo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgReg2BackArrow.setOnClickListener {
            startActivity(Intent(this, RegisterActivity1::class.java))
            finish()
        }

        val intentMail: String = intent.getStringExtra("mail").toString().trim { it <= ' ' }
        val intentPassword: String = intent.getStringExtra("password").toString().trim { it <= ' ' }

        binding.btnRegSignup.setOnClickListener {

            when (false) {
                !isEmptyField() -> Toast.makeText(
                    this,
                    getString(R.string.toast_empty_field),
                    Toast.LENGTH_SHORT
                ).show()
                !isValidUsername() -> Toast.makeText(
                    this,
                    getString(R.string.toast_username_size),
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(intentMail, intentPassword)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val username = binding.edtRegUsername.text.toString()
                                val userInfo = User(username)
                                db.child(auth.currentUser?.uid.toString()).setValue(userInfo)
                                Toast.makeText(
                                    this,
                                    getString(R.string.toast_success_register),
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    this,
                                    getString(R.string.toast_already_registered),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }

                }
            }
        }
    }

    private fun isValidUsername(): Boolean = with(binding) {
        return@with binding.edtRegUsername.text.toString().length < 10
    }

    private fun isEmptyField(): Boolean = with(binding) {
        return@with binding.edtRegUsername.text.toString().isEmpty()
    }
}
