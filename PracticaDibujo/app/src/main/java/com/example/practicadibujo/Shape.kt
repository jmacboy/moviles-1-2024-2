package com.example.practicadibujo

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.pow
import kotlin.math.sqrt

class Shape {

    var xStart = 0f
    var yStart = 0f
    var xEnd = 0f
    var yEnd = 0f
    var shapeType: ShapeType = ShapeType.CIRCLE


    fun draw(canvas: Canvas, objPaint: Paint) {
        when (shapeType) {
            ShapeType.CIRCLE -> {
                val radius =
                    sqrt((xEnd - xStart).pow(2) + (yEnd - yStart).pow(2))
                canvas.drawCircle(xStart, yStart, radius, objPaint)
            }

            ShapeType.RECTANGLE -> {
                canvas.drawRect(xStart, yStart, xEnd, yEnd, objPaint)
            }

            ShapeType.LINE -> {
                canvas.drawLine(xStart, yStart, xEnd, yEnd, objPaint)
            }
        }
    }

}