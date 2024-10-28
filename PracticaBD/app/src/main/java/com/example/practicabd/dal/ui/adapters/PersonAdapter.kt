package com.example.practicabd.dal.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicabd.dal.models.Person
import com.example.practicabd.databinding.PersonListItemBinding

class PersonAdapter(private var personList: List<Person>) :
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
        holder.bind(item)
    }

    fun updateData(newData: List<Person>?){
        personList = newData ?: arrayListOf()
        notifyDataSetChanged()
    }

    class PersonViewHolder(binding: PersonListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var lblFullName = binding.lblPersonFullName
        private var lblCity = binding.lblPersonCity
        fun bind(item: Person) {
            lblFullName.text = "${item.name} ${item.lastName}"
            lblCity.text = item.city
        }

    }
}