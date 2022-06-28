package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauthentication.databinding.ActivityRegister1Binding

class RegisterActivity1 : AppCompatActivity() {

    private lateinit var binding: ActivityRegister1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegister1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        listeners()
    }

    private fun listeners() {

        binding.imgReg1BackArrow.setOnClickListener {
            startActivity(Intent(this, StartActivity::class.java))
            finish()
        }

        binding.btnRegNext.setOnClickListener {

            when (false) {
                !isEmptyField() ->
                    Toast.makeText(this, getString(R.string.toast_empty_field), Toast.LENGTH_SHORT)
                        .show()
                isValidEmail() -> {
                    Toast.makeText(this, getString(R.string.toast_invalid_mail), Toast.LENGTH_SHORT)
                        .show()
                    binding.edtRegMail.error
                }
                !isValidPassword() -> Toast.makeText(
                    this,
                    getString(R.string.toast_pass_size),
                    Toast.LENGTH_SHORT
                ).show()
                else -> {
                    val intent = Intent(this, RegisterActivity2::class.java)
                    intent.putExtra("mail", binding.edtRegMail.text.toString())
                    intent.putExtra("password", binding.edtRegPassword.text.toString())
                    startActivity(intent)
                }
            }
        }
    }

    private fun isValidEmail(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(binding.edtRegMail.text.toString()).matches()

    private fun isEmptyField(): Boolean = with(binding) {
        return@with binding.edtRegMail.text.toString().isEmpty() ||
                binding.edtRegPassword.text.toString().isEmpty()
    }

    private fun isValidPassword(): Boolean = with(binding) {
        return@with binding.edtRegPassword.text.toString().length < 8
    }
//    private fun isAlreadyRegistered(): Boolean = with(binding) {
//        return@with if binding.edtRegMail.text.toString() ==
//    }
}