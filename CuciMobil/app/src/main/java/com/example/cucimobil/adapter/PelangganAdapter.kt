package com.example.cucimobil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cucimobil.Model.Pelanggan
import com.example.cucimobil.R

class PelangganAdapter(private val pelangganList: List<Pelanggan>) :
    RecyclerView.Adapter<PelangganAdapter.PelangganViewHolder>() {

    class PelangganViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvNomor: TextView = itemView.findViewById(R.id.tvNomor)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PelangganViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pelanggan, parent, false)
        return PelangganViewHolder(view)
    }

    override fun onBindViewHolder(holder: PelangganViewHolder, position: Int) {
        val pelanggan = pelangganList[position]
        holder.tvNama.text = pelanggan.nama
        holder.tvNomor.text = pelanggan.no_hp
        holder.tvAlamat.text = pelanggan.alamat
    }

    override fun getItemCount(): Int {
        return pelangganList.size
    }
}
