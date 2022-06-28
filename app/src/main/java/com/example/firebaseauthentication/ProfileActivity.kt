package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauthentication.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("userInfo")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            Toast.makeText(this, getString(R.string.toast_log_out), Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, StartActivity::class.java))
        }

        db.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val userInfo = snapshot.getValue(User::class.java) ?: return

                binding.txvProUsername.text = userInfo.username

            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }
}