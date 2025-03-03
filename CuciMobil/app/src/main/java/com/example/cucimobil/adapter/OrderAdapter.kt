package com.example.cucimobil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cucimobil.Model.Order
import com.example.cucimobil.R

class OrderAdapter(private val orderList: List<Order>) :
    RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTanggal: TextView = view.findViewById(R.id.txtTanggal)
        val txtNama: TextView = view.findViewById(R.id.txtNama)
        val txtNamaLayanan: TextView = view.findViewById(R.id.txtNamaLayanan)
        val txtHarga: TextView = view.findViewById(R.id.txtHarga)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaksi, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orderList[position]
        holder.txtTanggal.text = order.tanggal
        holder.txtNama.text = order.pelanggan_id.toString()
        holder.txtNamaLayanan.text = order.layanan_id.toString()
        holder.txtHarga.text = "Rp ${order.harga}"
    }

    override fun getItemCount(): Int = orderList.size
}
