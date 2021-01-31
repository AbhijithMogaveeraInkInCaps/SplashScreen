package com.abhijith.splashscreen.ui.view.group

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Property
import android.view.ViewGroup
import com.abhijith.splashscreen.ui.view.logMsg

class LogoView : ViewGroup {
    val logoViewGroup: LogoViewGroup = LogoViewGroup(context)
    val objectAnimator: ObjectAnimator = ObjectAnimator()

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?)
            : super(context, attrs)

    constructor(context: Context?) : super(context)

    var i = 100
    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
    }

    val m1 get() = width / 3
    var flag = false
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
            flag = true
            logoViewGroup.layout(
                (0 + m1+60) - i,
                (0)+120 - i,
                (width - m1-60) + i,
                (height - (m1 * 2)) + i
            )
            addView(logoViewGroup)
    }
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {

    }

    fun beginAnimation(onFinish: () -> Unit) {
        val l: Long = 4000
        //
        val translationY: ObjectAnimator = ObjectAnimator.ofFloat(
            logoViewGroup,
            "translationY",
            (height/6).toFloat()
        )
        translationY.duration = 500
        translationY.start()
        translationY.addUpdateListener {
            objectAnimator.apply {
                target = this@LogoView
                addUpdateListener {
                    invalidate()
                    logoViewGroup.layout(
//                        (0 + m1) - i,
//                        (0) - i,
//                        (width - m1) + i,
//                        (height - (m1 * 2)) + i

                        (0 + m1+60) - i,
                        (0)+120 - i,
                        (width - m1-60) + i,
                        (height - (m1 * 2)) + i

                    )
                }
                setProperty(object : Property<LogoView, Float>(Float::class.java, "Percent") {
                    override fun get(p0: LogoView?): Float {
                        return i.toFloat()

                    }

                    override fun set(rv: LogoView?, value: Float?) {
//                    super.set(rv, value)
                        value?.let {
                            i = value.toInt()
                            this@LogoView.invalidate()
                        }
                    }
                })
                removeAllListeners()
                setFloatValues(100.toFloat(), 4000.toFloat())
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

        //

    }

}