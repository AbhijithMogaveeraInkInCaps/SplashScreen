package com.abhijith.splashscreen.ui.view.group

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.ViewGroup
import com.abhijith.splashscreen.ui.view.RingView

class LogoViewGroup:ViewGroup {
    var circleOne:RingView=RingView(context, Color.BLACK)
    var circleTwo:RingView=RingView(context, Color.WHITE)
    var circleThree:RingView=RingView(context, Color.BLACK)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?)
            : super(context, attrs)

    constructor(context: Context?)
            : super(context) {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    val gap get() = width/6
    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        removeAllViews()
        circleOne.layout(0,0,width, height)
        circleTwo.layout(0+gap,0+gap,width-gap, height-gap)
        circleThree.layout(0+gap+gap,0+gap+gap,width-gap-gap, height-gap-gap)
        addView(circleOne)
        addView(circleTwo)
        addView(circleThree)
    }
}