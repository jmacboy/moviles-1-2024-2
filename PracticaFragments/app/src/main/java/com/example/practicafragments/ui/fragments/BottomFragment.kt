package com.example.practicafragments.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listapersonas.models.Contact
import com.example.practicafragments.R
import com.example.practicafragments.databinding.FragmentBottomBinding
import com.example.practicafragments.ui.activities.MainActivity
import com.example.practicafragments.ui.adapters.ContactAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass.
 * Use the [BottomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomFragment : Fragment(), ContactAdapter.OnContactClickListener {
    private lateinit var binding: FragmentBottomBinding
    private val datalist = arrayListOf(
        Contact(1, "Juan Perez", "1234567890"),
        Contact(2, "Maria Lopez", "0987654321"),
        Contact(3, "Pedro Sanchez", "1234567890"),
        Contact(4, "Ana Rodriguez", "0987654321"),
        Contact(5, "Luisa Perez", "1234567890"),
        Contact(6, "Carlos Lopez", "0987654321"),
        Contact(7, "Jose Sanchez", "1234567890"),
        Contact(8, "Laura Rodriguez", "0987654321"),
        Contact(9, "Jorge Perez", "1234567890"),
        Contact(10, "Marta Lopez", "0987654321"),
        Contact(11, "Rosa Sanchez", "1234567890"),
        Contact(12, "Pablo Rodriguez", "0987654321"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.fabAddContact.setOnClickListener {
//            buildAlertDialog()
            activity?.let {
                val mainActivity: MainActivity = it as MainActivity
                mainActivity.clearForm()
            }
        }
    }

    private fun buildAlertDialog(contact: Contact? = null) {

        if (context == null) return
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Formulario de contacto")


        val viewInflated: View = LayoutInflater.from(context)
            .inflate(R.layout.form_layout, null, false)

        val txtNewContactName: EditText = viewInflated.findViewById(R.id.txtNewContactName)
        val txtNewContactPhone: EditText = viewInflated.findViewById(R.id.txtNewContactPhone)
        txtNewContactName.setText(contact?.name)
        txtNewContactPhone.setText(contact?.phone)
        builder.setView(viewInflated)

        builder.setPositiveButton(android.R.string.ok) { dialog, _ ->
            dialog.dismiss()
            val name = txtNewContactName.text.toString()
            val phone = txtNewContactPhone.text.toString()

            if (contact != null) {
                contact.name = name
                contact.phone = phone
                editContactFromList(contact)
            } else {
                addContactToList(name, phone)
            }
        }
        builder.setNegativeButton(android.R.string.cancel) { dialog, _ ->
            dialog.cancel()
        }

        builder.show()
    }

    private fun setupRecyclerView() {
        binding.rvContactList.adapter = ContactAdapter(datalist, this)
        binding.rvContactList.layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }

    fun editContactFromList(contact: Contact) {
        val adapter = binding.rvContactList.adapter as ContactAdapter
        adapter.itemUpdated(contact)
    }

    fun addContactToList(name: String, phone: String) {
        val lastId = datalist.last().id + 1
        val contact = Contact(lastId, name, phone)
        val adapter = binding.rvContactList.adapter as ContactAdapter
        adapter.itemAdded(contact)
    }

    override fun onContactEditClickListener(contact: Contact) {
//        buildAlertDialog(contact)
        activity?.let {
            val mainActivity: MainActivity = it as MainActivity
            mainActivity.editContact(contact)
        }
    }

    override fun onContactDeleteClickListener(contact: Contact) {
        val adapter = binding.rvContactList.adapter as ContactAdapter
        adapter.itemDeleted(contact)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment BottomFragment.
         */
        @JvmStatic
        fun newInstance() = BottomFragment()
    }
}