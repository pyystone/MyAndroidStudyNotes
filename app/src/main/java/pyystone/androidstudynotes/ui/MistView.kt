package pyystone.androidstudynotes.ui

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Created by pyystone on 16/10/8.
 * email: pyystone@163.com
 * QQ: 862429936
 * github: https://github.com/pyystone
 */

class MistView : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (canvas == null) {
            return
        }
        drawImage(canvas)
        drawMist(canvas)
    }

    private fun drawImage(canvas: Canvas) {

    }

    private fun drawMist(canvas: Canvas) {

    }

}