package com.example.practicafragments.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.listapersonas.models.Contact
import com.example.practicafragments.databinding.FragmentTopBinding
import com.example.practicafragments.ui.activities.MainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [TopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopFragment : Fragment() {
    var currentContact: Contact? = null

    private lateinit var binding: FragmentTopBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTopBinding.inflate(inflater, container, false)
        setupEventListeners()

        return binding.root
    }

    private fun setupEventListeners() {
//        binding.btnShowToast.setOnClickListener {
//            Toast.makeText(context, "Prueba fragment", Toast.LENGTH_SHORT).show()
//        }
        binding.btnSaveFragment.setOnClickListener {
            val name = binding.txtContactName.text.toString()
            val phone = binding.txtContactPhone.text.toString()
            val isInsert = currentContact == null
            if (isInsert) {
                currentContact = Contact(0, name, phone)
            } else {
                currentContact?.name = name
                currentContact?.phone = phone
            }
            activity?.let {
                val mainActivity: MainActivity = it as MainActivity
                mainActivity.saveContact(currentContact!!, isInsert)
            }
        }
    }

    fun clearForm() {
        binding.txtContactName.setText("")
        binding.txtContactPhone.setText("")
        currentContact = null
    }

    fun editContact(contact: Contact) {
        binding.txtContactName.setText(contact.name)
        binding.txtContactPhone.setText(contact.phone)
        currentContact = contact
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment TopFragment.
         */
        @JvmStatic
        fun newInstance() = TopFragment()
    }
}