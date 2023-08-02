package com.example.uas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uas.databinding.ActivityRincianObatBinding
import com.squareup.picasso.Picasso


class RincianObat : AppCompatActivity() {
    private lateinit var binding: ActivityRincianObatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRincianObatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val img = intent.getStringExtra("img")
        val nama = intent.getStringExtra("nama")
        val isi = intent.getStringExtra("isi")
        val ukuran = intent.getStringExtra("ukuran")
        val detail = intent.getStringExtra("detail")
        val dosis = intent.getStringExtra("dosis")
        Picasso.get().load(img).into(binding.img1)
        binding.txt1.text = nama
        binding.txt2.text = isi
        binding.txt3.text = ukuran
        binding.txt4.text = detail
        binding.txt6.text = dosis

        binding.btnback.setOnClickListener{
            val intent = Intent(this, MenuObat::class.java)
            startActivity(intent)
        }

        binding.btnback2.setOnClickListener{
            val intent = Intent(this, MenuObat::class.java)
            startActivity(intent)
        }
    }

}