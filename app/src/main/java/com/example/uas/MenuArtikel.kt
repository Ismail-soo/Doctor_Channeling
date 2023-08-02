package com.example.uas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uas.databinding.ActivityMenuArtikelBinding
import com.example.uas.fragment.HomeFragment

class MenuArtikel : AppCompatActivity() {
    private lateinit var binding: ActivityMenuArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.a1.setOnClickListener {
            val intent = Intent(this, Artikel::class.java)
            startActivity(intent)
        }

        binding.a2.setOnClickListener {
            val intent = Intent(this, Artikel2::class.java)
            startActivity(intent)
        }

        binding.a3.setOnClickListener {
            val intent = Intent(this, Artikel3::class.java)
            startActivity(intent)
        }

        binding.a4.setOnClickListener {
            val intent = Intent(this, Artikel4::class.java)
            startActivity(intent)
        }

        binding.btnback2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}