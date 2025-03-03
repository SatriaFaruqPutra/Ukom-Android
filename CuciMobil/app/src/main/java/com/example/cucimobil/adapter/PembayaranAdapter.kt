package com.example.cucimobil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cucimobil.Model.Pembayaran
import com.example.cucimobil.R

class PembayaranAdapter(private val pembayaranList: List<Pembayaran>) :
    RecyclerView.Adapter<PembayaranAdapter.PembayaranViewHolder>() {

    class PembayaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNama: TextView = itemView.findViewById(R.id.txtNama)
        val txtJumlah: TextView = itemView.findViewById(R.id.txtJumlah)
        val txtBayar: TextView = itemView.findViewById(R.id.txtBayar)
        val txtKembali: TextView = itemView.findViewById(R.id.txtKembali)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PembayaranViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pembayaran, parent, false)
        return PembayaranViewHolder(view)
    }

    override fun onBindViewHolder(holder: PembayaranViewHolder, position: Int) {
        val pembayaran = pembayaranList[position]

        holder.txtJumlah.text = "Jumlah: Rp ${pembayaran.jumlah}"
        holder.txtBayar.text = "Bayar: Rp ${pembayaran.bayar}"
        holder.txtKembali.text = "Kembali: Rp ${pembayaran.kembali}"
    }

    override fun getItemCount(): Int = pembayaranList.size
}
