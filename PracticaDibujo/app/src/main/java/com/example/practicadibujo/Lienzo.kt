package com.example.practicadibujo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class Lienzo(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var shapeArray = ArrayList<Shape>()
    private var shape: ShapeType = ShapeType.CIRCLE
    private val objPaint = Paint().apply {
        color = Color.BLACK
        strokeWidth = 5f
    }
    private var currentShape: Shape? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (shape in shapeArray) {
            shape.draw(canvas, objPaint)
        }
        currentShape?.draw(canvas, objPaint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return false
        }
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentShape = Shape().apply {
                    xStart = x
                    yStart = y
                    xEnd = x
                    yEnd = y
                    shapeType = shape
                }
                invalidate()
            }

            MotionEvent.ACTION_MOVE -> {
                currentShape?.apply {
                    xEnd = x
                    yEnd = y
                }
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                currentShape?.apply {
                    xEnd = x
                    yEnd = y
                }
                shapeArray.add(currentShape!!)
                invalidate()
            }
        }

        return true
    }

    fun setShape(theShape: ShapeType) {
        this.shape = theShape
    }
}