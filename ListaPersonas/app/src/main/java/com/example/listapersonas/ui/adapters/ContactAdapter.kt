package com.example.listapersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listapersonas.R
import com.example.listapersonas.models.Contact

class ContactAdapter(
    private val contactList: ArrayList<Contact>,
    private val listener: OnContactClickListener
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
        holder.bind(contactList[position], listener)
    }

    fun itemAdded(contact: Contact) {
        contactList.add(1, contact)
        notifyItemInserted(1)
//        notifyDataSetChanged()
    }

    fun itemDeleted(contact: Contact) {
        val index = contactList.indexOf(contact)
        contactList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun itemUpdated(contact: Contact) {
        val index = contactList.indexOf(contact)
        contactList[index] = contact
        notifyItemChanged(index)
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lblContactItemName = itemView.findViewById<TextView>(R.id.lblContactItemName)
        private var lblContactItemPhone = itemView.findViewById<TextView>(R.id.lblContactItemPhone)
        private var btnEditContactItem = itemView.findViewById<ImageButton>(R.id.btnEditContactItem)
        private var btnDeleteContactItem =
            itemView.findViewById<ImageButton>(R.id.btnDeleteContactItem)

        fun bind(contact: Contact, listener: OnContactClickListener) {
            lblContactItemName.text = contact.name
            lblContactItemPhone.text = contact.phone
            btnEditContactItem.setOnClickListener {
                listener.onContactEditClickListener(contact)
            }
            btnDeleteContactItem.setOnClickListener {
                listener.onContactDeleteClickListener(contact)
            }
        }
    }

    public interface OnContactClickListener {
        fun onContactEditClickListener(contact: Contact)
        fun onContactDeleteClickListener(contact: Contact)
    }
}