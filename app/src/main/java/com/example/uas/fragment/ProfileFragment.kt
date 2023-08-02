package com.example.uas.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uas.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var userRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userRef = FirebaseDatabase.getInstance().reference


        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = it.displayName
            val email = it.email
            val photoUrl = it.photoUrl

            //binding.namatxt.text = name

            binding.emailtxt.text = email
            binding.emailtxt2.text = email

            // Check if user's email is verified
            val emailVerified = it.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            val uid = it.uid
            userRef.child("user").child(uid).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Check if the 'nama' field exists for the given user ID
                    if (snapshot.hasChild("nama")) {
                        // Retrieve the 'nama' value
                        val nama = snapshot.child("nama").value.toString()
                        Log.d("NAMA", nama)
                        // Update the TextView with the retrieved 'nama' value
                        binding.namatxt.text = nama
                        val namatxt2 = binding.namatxt2
                        namatxt2.text = nama
                    } else {
                        // The 'nama' field doesn't exist for the given user ID.
                        // Handle this case as per your requirements.
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle database read error if needed
                }
            })

        }

    }


}