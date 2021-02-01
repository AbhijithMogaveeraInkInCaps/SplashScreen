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

    private val paint = Paint().apply {
        color = Color.WHITE
    }
    private val objectAnimator: ObjectAnimator = ObjectAnimator()
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


    /**
     * @param animationDuration duration in which animation should complete
     * @param onStart called when the animation starts
     * @param onFinish called when animation ends
    * */
    fun beginAnimation(
        animationDuration:Long,
        onStart:()->Unit,
        onFinish: () -> Unit
    ) {
        changingX = width
        val l: Long = animationDuration
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