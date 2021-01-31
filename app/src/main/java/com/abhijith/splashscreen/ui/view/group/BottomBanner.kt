package com.abhijith.splashscreen.ui.view.group

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import com.abhijith.splashscreen.ui.view.WhiteScreenViewRTL

class BottomBanner : ViewGroup {
    val mainText: TextView = TextView(context).apply {
        text = "Follow"
        gravity = Gravity.CENTER
        setTypeface(typeface, Typeface.BOLD)
        setTextColor(Color.BLACK)
    }
    val subText: TextView = TextView(context).apply {
        text = "Follow ur passion"
        gravity = Gravity.CENTER
        setTypeface(typeface, Typeface.BOLD_ITALIC)
    }

    val whiteScreenViewRTL = WhiteScreenViewRTL(context)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?)
            : super(context, attrs)

    constructor(context: Context?) : super(context)

    val m1 get() = width / 3

    init {
        addView(mainText)
        addView(subText)
        addView(whiteScreenViewRTL)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        mainText.layout(0, 0, width, height)
        subText.layout(0, 0 + 100, width, height)
        whiteScreenViewRTL.layout(0, 0, width, height)
    }

    fun beginAnimation(onFinish: () -> Unit) {
        whiteScreenViewRTL.beginAnimation(width, {
            onFinish()
        }, {})
    }
}