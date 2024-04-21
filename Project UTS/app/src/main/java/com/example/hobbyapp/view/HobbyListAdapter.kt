package com.example.hobbyapp.view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hobbyapp.databinding.FragmentCardHobbyBinding
import com.example.hobbyapp.model.Hobby
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class HobbyListAdapter(val hobbyList: ArrayList<Hobby>)
    : RecyclerView.Adapter<HobbyListAdapter.HobbyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HobbyViewHolder {
        val binding = FragmentCardHobbyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HobbyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return hobbyList.size
    }

    override fun onBindViewHolder(holder: HobbyViewHolder, position: Int) {
        holder.binding.txtJudul.text = hobbyList[position].judul
        holder.binding.txtDeskripsi.text = hobbyList[position].deskripsi
        val userPembuat = hobbyList[position].pembuat
        holder.binding.txtPembuat.text = "${userPembuat?.namaDepan} ${userPembuat?.namaBelakang}"

        val picasso = Picasso.Builder(holder.itemView.context).build()
        picasso.load(hobbyList[position].gambar_url)
            .into(holder.binding.imgCard, object : Callback {
                override fun onSuccess() {
                    holder.binding.imgCard.visibility = View.VISIBLE
                }

                override fun onError(e: Exception?) {
                    Log.e("Picasso_error", e.toString())
                }
            })

        holder.binding.btnRead.setOnClickListener{

        }
    }

    fun updateHobbyList(newHobbyList: ArrayList<Hobby>) {
        hobbyList.clear()
        hobbyList.addAll(newHobbyList)
        notifyDataSetChanged()
    }

    inner class HobbyViewHolder(val binding: FragmentCardHobbyBinding) : RecyclerView.ViewHolder(binding.root)
}

