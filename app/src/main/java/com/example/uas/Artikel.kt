package com.example.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uas.databinding.ActivityArtikelBinding
import com.example.uas.databinding.ActivityMenuArtikelBinding
import com.example.uas.fragment.HomeFragment

class Artikel : AppCompatActivity() {
    private lateinit var binding: ActivityArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityArtikelBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnback2.setOnClickListener {
            val intent = Intent(this, MenuArtikel::class.java)
            startActivity(intent)
        }
    }
}