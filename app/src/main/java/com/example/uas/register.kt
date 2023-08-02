package com.example.uas

import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.uas.databinding.ActivityLoginBinding
import com.example.uas.databinding.ActivityRegisterBinding
import com.example.uas.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    lateinit var databaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        databaseRef = FirebaseDatabase.getInstance().getReference("user")

        binding.btnRegister.setOnClickListener{
            val userEmail = binding.inputEmail.text.toString()
            val userPassword = binding.inputpass.text.toString()
            val userName = binding.inputName.text.toString()

            if (userEmail.isNotEmpty() && userPassword.isNotEmpty() && userName.isNotEmpty()){
                //updateProfile(userName)
                register(userName,userEmail,userPassword)
            } else {
                Toast.makeText(
                    baseContext,
                    "Email atau Password tidak boleh kosong.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }

        binding.register.setOnClickListener {
            val intent = Intent(this,login::class.java)
            startActivity(intent)
        }

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
    }

    private fun updateProfile(userName: String) {
        // [START update_profile]
        val user = Firebase.auth.currentUser

        val profileUpdates = userProfileChangeRequest {
            displayName = userName
            photoUri = Uri.parse("https://example.com/jane-q-user/profile.jpg")
        }

        user!!.updateProfile(profileUpdates)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "User profile updated.")
                }
            }
        // [END update_profile]
    }


    private fun register(userName: String, email: String, password: String) {
        val user = Firebase.auth.currentUser

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this)
        { task ->
                if (task.isSuccessful) {
                    val uss = Firebase.auth.currentUser
                    uss?.let {
                        val uid = it.uid
                        val mahasiswaData = User(uid,userName)
                        databaseRef.child(uid!!).setValue(mahasiswaData)
                    }
                    val intent = Intent(this,login::class.java)
                    startActivity(intent)
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Email atau Password Salah.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }


    }

    private fun updateUI(user: FirebaseUser?) {

    }

}