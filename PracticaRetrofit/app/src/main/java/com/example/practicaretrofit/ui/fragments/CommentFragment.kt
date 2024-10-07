package com.example.practicaretrofit.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaretrofit.databinding.FragmentCommentItemListBinding
import com.example.practicaretrofit.ui.adapters.CommentAdapter
import com.example.practicaretrofit.ui.viewmodels.CommentListViewModel


/**
 * A fragment representing a list of Items.
 */
class CommentFragment : Fragment() {

    private var postId: Int = 0
    private var columnCount = 1

    private val viewModel: CommentListViewModel by viewModels()
    private lateinit var binding: FragmentCommentItemListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = 1
            postId = it.getInt(ARG_POST_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommentItemListBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set the adapter
        with(view) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context)
                else -> GridLayoutManager(context, columnCount)
            }
            adapter = CommentAdapter(arrayListOf())
            val dividerItemDecoration = DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation
            )
            addItemDecoration(dividerItemDecoration)
        }

        setupViewModelObservers()
        viewModel.getComments(postId)
        return view
    }

    private fun setupViewModelObservers() {
        viewModel.commentList.observe(viewLifecycleOwner) {
            val adapter = binding.list.adapter as CommentAdapter
            adapter.updateData(it)
        }
    }

    companion object {

        const val ARG_POST_ID = "postId"

        @JvmStatic
        fun newInstance(postId: Int) =
            CommentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_POST_ID, postId)
                }
            }
    }
}