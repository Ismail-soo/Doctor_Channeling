package com.example.uas.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.uas.RincianObat
import com.example.uas.databinding.ActivityRincianObatBinding
import com.example.uas.databinding.ListBinding
import com.squareup.picasso.Picasso

class Adapter(private val userList : ArrayList<Obat>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder (val binding : ListBinding, val bindingg: ActivityRincianObatBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val itemView2 = ActivityRincianObatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView,itemView2)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentitem = userList[position]

        Picasso.get().load(currentitem.img).into(holder.binding.img)
        holder.binding.txtob1.text = currentitem.nama
        holder.binding.txtob2.text = currentitem.isi
        holder.binding.txtob3.text = currentitem.ukuran


        val context = holder.itemView.context
        holder.binding.btnob.setOnClickListener{
            val intent = Intent(context, RincianObat::class.java)
            intent.putExtra("img",currentitem.img)
            intent.putExtra("nama",currentitem.nama)
            intent.putExtra("isi",currentitem.isi)
            intent.putExtra("ukuran",currentitem.ukuran)
            intent.putExtra("detail",currentitem.detail)
            intent.putExtra("dosis",currentitem.dosis)
            context.startActivity(intent)

        }
    }


    fun setData(newData: ArrayList<Obat>) {
        userList.clear()
        userList.addAll(newData)
        notifyDataSetChanged()
    }
}