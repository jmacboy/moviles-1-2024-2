package com.example.practicacomponentespersonalizados

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.practicacomponentespersonalizados.databinding.SelectorNumerosLayoutBinding

class SelectorNumeros(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private var binding: SelectorNumerosLayoutBinding
    var value = 0

    init {
        val inflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = SelectorNumerosLayoutBinding.inflate(inflater, this, true)

        setupEventListeners()
    }

    private fun setupEventListeners() {
        binding.btnPlus.setOnClickListener {
            value++
            reloadResult()
        }
        binding.btnMinus.setOnClickListener {
            value--
            reloadResult()
        }
    }

    private fun reloadResult() {
        binding.lblResult.text = value.toString()
    }
}