package com.example.listapersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listapersonas.R
import com.example.listapersonas.models.Contact

class ContactAdapter(
    private val contactList: ArrayList<Contact>
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ContactViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.contacto_item_layout, parent, false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contactList[position])
    }

    fun itemAdded(contact: Contact) {
        contactList.add(1, contact)
        notifyItemInserted(1)
//        notifyDataSetChanged()
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lblContactItemName = itemView.findViewById<TextView>(R.id.lblContactItemName)
        private var lblContactItemPhone = itemView.findViewById<TextView>(R.id.lblContactItemPhone)
        fun bind(contact: Contact) {
            lblContactItemName.text = contact.name
            lblContactItemPhone.text = contact.phone
        }
    }
}