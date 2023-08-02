package com.example.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uas.databinding.ActivityArtikel2Binding
import com.example.uas.databinding.ActivityArtikelBinding

class Artikel2 : AppCompatActivity() {
    private lateinit var binding: ActivityArtikel2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityArtikel2Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnback2.setOnClickListener {
            val intent = Intent(this, MenuArtikel::class.java)
            startActivity(intent)
        }
    }
}