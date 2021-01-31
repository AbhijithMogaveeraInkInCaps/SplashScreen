package com.abhijith.splashscreen.ui.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.util.Property
import android.view.View

class WhiteScreenViewRTL(context: Context?) : View(context) {

    val paint = Paint().apply {
        color = Color.WHITE
    }
    val objectAnimator: ObjectAnimator = ObjectAnimator()
    var changingX: Int = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("ChangeInX",(width - changingX).toString())
        canvas?.let {
            it.drawRect(
                width.toFloat()-changingX,
                0.toFloat(),
                width.toFloat(),
                height.toFloat(),
                paint
            )
        }
    }


    var isAnimationStarted:Boolean = false
    fun beginAnimation(x: Int, onFinish: () -> Unit,onStart:()->Unit) {

        changingX = x

        val l: Long = 2000

        objectAnimator.apply {

            target = this@WhiteScreenViewRTL

            addUpdateListener {
                invalidate()
            }

            setProperty(object : Property<WhiteScreenViewRTL, Float>(Float::class.java, "Percent") {
                override fun get(p0: WhiteScreenViewRTL?): Float {
                    return changingX.toFloat()
                }

                override fun set(rv: WhiteScreenViewRTL?, value: Float?) {
                    value?.let {
                        changingX = value.toInt()
                    }
                }
            })

            removeAllListeners()

            setFloatValues(0.toFloat(),changingX.toFloat())

            duration = l

            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    onFinish()
                    removeAllListeners()
                }
                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    onStart()
                }
            })

            start()

        }

    }

}