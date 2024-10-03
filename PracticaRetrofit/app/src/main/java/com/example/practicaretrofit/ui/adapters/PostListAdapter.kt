package com.example.practicaretrofit.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaretrofit.R
import com.example.practicaretrofit.databinding.PostItemLayoutBinding
import com.example.practicaretrofit.models.Post
import com.example.practicaretrofit.models.Posts

class PostListAdapter() : RecyclerView.Adapter<PostListAdapter.PostItemViewHolder>() {
    private var postList: Posts = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder {
        val binding =
            PostItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostItemViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    fun updateData(it: Posts) {
        postList = it
        notifyDataSetChanged()
    }

    class PostItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val lblPostItemTitle: TextView = itemView.findViewById(R.id.lblPostItemTitle)
        fun bind(post: Post) {
            lblPostItemTitle.text = post.title
        }
    }
}