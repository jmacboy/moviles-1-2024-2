package com.example.practicabd.dal.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicabd.dal.models.Person
import com.example.practicabd.databinding.PersonListItemBinding

class PersonAdapter(
    private var personList: List<Person>,
    private val listener: PersonaItemListener?
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(
            PersonListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val item = personList[position]
        holder.bind(item, listener)
    }

    fun updateData(newData: List<Person>?) {
        personList = newData ?: arrayListOf()
        notifyDataSetChanged()
    }

    fun removeItem(it: Person) {
        var index = -1
        personList.forEachIndexed { i, person ->
            if (person.id == it.id) {
                index = i
                return@forEachIndexed
            }
        }
        if (index == -1) {
            return
        }
        Log.d("ELIMINADO", index.toString())
        personList = personList.toMutableList().apply {
            removeAt(index)
        }
        notifyItemRemoved(index)
    }

    class PersonViewHolder(binding: PersonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var lblFullName = binding.lblPersonFullName
        private var lblCity = binding.lblPersonCity
        fun bind(item: Person, listener: PersonaItemListener?) {
            lblFullName.text = "${item.name} ${item.lastName}"
            lblCity.text = item.city
            itemView.setOnClickListener {
                listener?.onPersonClick(item)
            }
            itemView.isLongClickable = true
            itemView.setOnLongClickListener {
                listener?.onPersonLongClick(item)
                itemView.showContextMenu()
                true
            }
        }

    }

    interface PersonaItemListener {
        fun onPersonClick(person: Person)
        fun onPersonLongClick(person: Person)
    }
}