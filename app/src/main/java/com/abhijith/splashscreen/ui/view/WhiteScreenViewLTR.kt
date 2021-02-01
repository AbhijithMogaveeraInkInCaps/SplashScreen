package com.abhijith.splashscreen.ui.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Property
import android.view.View

class WhiteScreenViewLTR constructor(context: Context?,)
    : View(context) {

    val paint = Paint().apply {
        color = Color.WHITE
    }
    val objectAnimator: ObjectAnimator = ObjectAnimator()
    var changingX: Int = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.drawRect(
                    width.toFloat(),
                    height.toFloat(),
                    0.toFloat() + (width - changingX),
                    0.toFloat(),
                    paint
                )
        }
    }


    /**
     * @param animationDuration duration in which animation should complete
     * @param onStart called when the animation starts
     * @param onFinish called when animation ends
     * */
    fun beginAnimation(
        animationDuration:Long,
        onStart: () -> Unit,
        onFinish: () -> Unit,
    ) {
        changingX = width
        val l: Long = animationDuration
        objectAnimator.apply {
            target = this@WhiteScreenViewLTR
            addUpdateListener {
                invalidate()
            }
            setProperty(object : Property<WhiteScreenViewLTR, Float>(Float::class.java, "Percent") {
                override fun get(p0: WhiteScreenViewLTR?): Float {
                    return changingX.toFloat()
                }

                override fun set(rv: WhiteScreenViewLTR?, value: Float?) {
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

                override fun onAnimationStart(animation: Animator?) {
                    super.onAnimationStart(animation)
                    onStart()
                }
            })
            start()
        }
    }
}