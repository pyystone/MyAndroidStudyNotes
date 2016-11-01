package com.androidstudynotes.ui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Created by pyystone on 16/9/29.
 * email: pyystone@163.com
 * QQ: 862429936
 * github: https://github.com/pyystone
 */

class SnowView : View {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        drawSnow(canvas)
    }

    private fun drawSnow(canvas: Canvas) {

    }

    fun startSnow() {

    }
}