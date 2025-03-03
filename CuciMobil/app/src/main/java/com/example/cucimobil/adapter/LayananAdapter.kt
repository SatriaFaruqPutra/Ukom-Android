package com.example.cucimobil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cucimobil.Model.Layanan
import com.example.cucimobil.R

class LayananAdapter(private var layananList: MutableList<Layanan>) :
    RecyclerView.Adapter<LayananAdapter.LayananViewHolder>() {

    class LayananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNamaLayanan: TextView = itemView.findViewById(R.id.tvNamaLayanan)
        val tvJenisKendaraan: TextView = itemView.findViewById(R.id.tvJenisKendaraan)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
        val tvHarga: TextView = itemView.findViewById(R.id.tvHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LayananViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layanan, parent, false)
        return LayananViewHolder(view)
    }

    override fun onBindViewHolder(holder: LayananViewHolder, position: Int) {
        val layanan = layananList[position]
        holder.tvNamaLayanan.text = layanan.nama_layanan
        holder.tvJenisKendaraan.text = "Jenis: ${layanan.jenis_kendaraan}"
        holder.tvDeskripsi.text = layanan.deskripsi
        holder.tvHarga.text = "Harga: Rp ${layanan.harga}"
    }

    override fun getItemCount(): Int {
        return layananList.size
    }

    // 🔥 Tambahkan method ini agar data bisa diperbarui tanpa buat ulang adapter
    fun updateData(newData: List<Layanan>) {
        layananList.clear()
        layananList.addAll(newData)
        notifyDataSetChanged()
    }
}
