package com.example.practicaapiusuarios.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapiusuarios.databinding.UserItemLayoutBinding
import com.example.practicaapiusuarios.models.User
import com.example.practicaapiusuarios.models.Users

class UserListAdapter(
    private var userList: Users
) : RecyclerView.Adapter<UserListAdapter.UserItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(
            UserItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = userList[position]
        holder.bind(item)
    }

    fun updateData(newList: Users) {
        this.userList = newList
        notifyDataSetChanged()
    }

    class UserItemViewHolder(binding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val lblEmail = binding.lblUserItemEmail
        private val lblId = binding.lblUserItemId
        fun bind(item: User) {
            lblEmail.text = item.email
            lblId.text = item.id.toString()
        }
    }

}