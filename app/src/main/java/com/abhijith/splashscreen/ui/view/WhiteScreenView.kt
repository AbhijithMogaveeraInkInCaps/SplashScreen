package com.abhijith.splashscreen.ui.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Property
import android.view.View

class WhiteScreenView constructor(context: Context?, val rtl: Boolean) : View(context) {

    var clr: Int = Color.BLACK

    val paint = Paint().apply {
        color = Color.WHITE
    }
    val objectAnimator: ObjectAnimator = ObjectAnimator()
    var changingX: Int = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            if (rtl) {
                it.drawRect(
                    0.toFloat() + (width - changingX),
                    0.toFloat(),
                    width.toFloat(),
                    height.toFloat(),
                    paint
                )
            } else {
                it.drawRect(
                    0.toFloat(),
                    0.toFloat(),
                    width.toFloat() - changingX,
                    height.toFloat(),
                    paint
                )
            }
        }
    }



    fun beginAnimation(x: Int, onFinish: () -> Unit) {
        changingX = x
        val l: Long = 2000
        objectAnimator.apply {
            target = this@WhiteScreenView
            addUpdateListener {
                invalidate()
            }
            setProperty(object : Property<WhiteScreenView, Float>(Float::class.java, "Percent") {
                override fun get(p0: WhiteScreenView?): Float {
                    return changingX.toFloat()

                }

                override fun set(rv: WhiteScreenView?, value: Float?) {
                    value?.let {
                        changingX = value.toInt()
                    }
                }
            })
            removeAllListeners()
            setFloatValues(changingX.toFloat(), 0.toFloat())
            duration = l
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    onFinish()
                    removeAllListeners()
                }
            })
            start()
        }
    }
}