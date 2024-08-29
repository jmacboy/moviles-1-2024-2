package com.example.practicarecyclerview.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicarecyclerview.R

class NombresAdapter(
    private val nombres: ArrayList<String>
) : RecyclerView.Adapter<NombresAdapter.NombreViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NombreViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.nombres_list_item, parent, false)
        return NombreViewHolder(view)
    }

    override fun getItemCount(): Int {
        return nombres.size
    }

    override fun onBindViewHolder(holder: NombreViewHolder, position: Int) {
        holder.bind(nombres[position])
    }


    class NombreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lblItemName: TextView = itemView.findViewById(R.id.lblItemName)
        fun bind(name: String) {
            lblItemName.text = name
        }
    }
}