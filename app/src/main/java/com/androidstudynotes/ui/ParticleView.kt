package com.androidstudynotes.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

/**
 * Created by pyystone on 16/9/26.
 * email: pyystone@163.com
 * QQ: 862429936
 * github: https://github.com/pyystone
 */

class ParticleView : View {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        if (canvas == null) {
            return
        }
        drawHelloWorldTxt(canvas)
    }

    private fun drawHelloWorldTxt(canvas: Canvas) {

        val txtPaint = Paint()
        txtPaint.textSize = 100f
        txtPaint.color = Color.RED

        val txtPaint2 = Paint()
        txtPaint2.textSize = 100f
        txtPaint2.color = Color.BLACK

        val rectPaint = Paint()
        rectPaint.color = Color.BLUE

        val txt = "Hello World!"

        val widths = FloatArray(txt.length)
        txtPaint.getTextWidths(txt,widths)

        var widthSum = 0f
        for (width in widths) {
            widthSum += width
        }
        val rect = Rect()
        rect.set(110,110, (110 + widthSum * 0.78).toInt(),220)

        canvas.drawText(txt,rect.left + 1f,rect.bottom - txtPaint2.fontMetrics.descent,txtPaint2)

        canvas.drawRect(rect,rectPaint)
        canvas.clipRect(rect)
        canvas.drawText(txt,rect.left + 1f,rect.bottom - txtPaint.fontMetrics.descent,txtPaint)
    }
}