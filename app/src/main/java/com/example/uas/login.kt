package com.example.uas

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.uas.databinding.ActivityLoginBinding
import com.example.uas.fragment.HomeFragment
import com.example.uas.fragment.ProfileFragment
import com.example.uas.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    lateinit var databaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        databaseRef = FirebaseDatabase.getInstance().getReference("user")

        binding.btnRegister.setOnClickListener{
            val userEmail = binding.inputEmail.text.toString()
            val userPassword = binding.inputPassword.text.toString()

            if (userEmail.isNotEmpty() && userPassword.isNotEmpty()){
                signIn(userEmail,userPassword)
            }
            else {
                Toast.makeText(
                    baseContext,
                    "Email atau Password tidak boleh kosong.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }

        binding.register.setOnClickListener {
            val intent = Intent(this,register::class.java)
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

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val postListener = object : ValueEventListener {
                        override fun onDataChange(dataSnapshot: DataSnapshot) {
                            // Get Post object and use the values to update the UI
                            val uss = Firebase.auth.currentUser
                            uss?.let {
                                val uid = it.uid
                                val post = dataSnapshot.child(uid).getValue(User::class.java)
                                post?.let {
                                    val name = it.nama
                                    //  Log.d(TAG, name)
                                    val bundle = Bundle()
                                    bundle.putString("namee", name)
                                }
                            }
                        }

                        override fun onCancelled(databaseError: DatabaseError) {
                            // Getting Post failed, log a message
                            Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                        }
                    }
                    databaseRef.addValueEventListener(postListener)

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
        // [END sign_in_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
    }

}