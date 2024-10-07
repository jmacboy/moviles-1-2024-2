package com.example.practicaretrofit.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaretrofit.databinding.FragmentCommentItemBinding
import com.example.practicaretrofit.models.Comment
import com.example.practicaretrofit.models.Comments

/**
 * [RecyclerView.Adapter] that can display a [Comment].
 */
class CommentAdapter(
    private var values: List<Comment>
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCommentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size
    fun updateData(newCommentList: Comments) {
        this.values = newCommentList
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: FragmentCommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val contentView: TextView = binding.lblCommentItemText
        private val lblEmail: TextView = binding.lblCommentItemMail
        private val lblName: TextView = binding.lblCommentItemName

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }

        fun bind(item: Comment) {
            contentView.text = item.body
            lblEmail.text = item.email
            lblName.text = item.name
        }
    }

}