package com.example.practicacomponentespersonalizados

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import com.example.practicacomponentespersonalizados.databinding.PaginadorLayoutBinding

class Paginador(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var binding: PaginadorLayoutBinding
    private var btnArray: Array<Button>

    init {
        val inflater =
            context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        binding = PaginadorLayoutBinding.inflate(inflater, this, true)

        binding.apply {
            btnArray = arrayOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10)
        }
        setupEventListeners()
    }

    private fun setupEventListeners() {
        for (i in btnArray.indices) {
            btnArray[i].setOnClickListener {
                selectPage(i)
            }
        }
    }

    private fun selectPage(pageNo: Int) {
        for (i in btnArray.indices) {
            btnArray[i].setBackgroundColor(context.getColor(R.color.black))
        }
        btnArray[pageNo].setBackgroundColor(context.getColor(R.color.red))
    }
}