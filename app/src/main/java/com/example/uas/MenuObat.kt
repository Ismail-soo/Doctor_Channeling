package com.example.uas

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.databinding.ActivityMenuObatBinding
import com.example.uas.fragment.HomeFragment
import com.example.uas.model.Adapter
import com.example.uas.model.Obat
import com.google.firebase.database.*
import java.util.EventListener

class MenuObat : AppCompatActivity() {
    private lateinit var binding: ActivityMenuObatBinding
    private lateinit var adapter: Adapter
    private lateinit var dbref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuObatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = Adapter(ArrayList())
        binding.userList.adapter = adapter
        binding.userList.layoutManager = LinearLayoutManager(this)
        dbref = FirebaseDatabase.getInstance().getReference("obat")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                val obatList = ArrayList<Obat>()
                for (snapshot in datasnapshot.children) {
                    val obat = snapshot.getValue(Obat::class.java)
                    obat?.let { obatList.add(it) }
                }
                adapter.setData(obatList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


        binding.btnback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
