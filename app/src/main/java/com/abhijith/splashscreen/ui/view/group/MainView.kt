package com.abhijith.splashscreen.ui.view.group

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.abhijith.splashscreen.ui.IntroActivity
import com.abhijith.splashscreen.ui.view.WhiteScreenView
import com.abhijith.splashscreen.ui.view.logMsg

class MainView : ViewGroup {

    val logoViewGroup: LogoView = LogoView(context)
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
    val whiteScreenViewMain = WhiteScreenView(context, true)
    val whiteScreenViewSub = WhiteScreenView(context, false)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?)
            : super(context, attrs)

    constructor(context: Context?)
            : super(context) {
    }

    val m1 get() = width / 3
    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        val i = 100
        removeAllViews()
        logoViewGroup.layout(0, 0, width, height)
        mainText.layout(0, height / 2, width, height - m1 - 300)
        subText.layout(0, (height / 2) + 100, width, height - m1 - 300)

        whiteScreenViewMain.layout(0, 0, width, height)
        whiteScreenViewSub.layout(0, height / 2, width, height - m1 - 300)

        addView(logoViewGroup)
        addView(mainText)
        addView(subText)
        addView(whiteScreenViewSub.apply {
            visibility = INVISIBLE
        })

        addView(whiteScreenViewMain.also {
            it.beginAnimation(width) {
                whiteScreenViewSub.visibility = VISIBLE
                whiteScreenViewSub.beginAnimation(width) {
                    mainText.visibility = INVISIBLE
                    subText.visibility = INVISIBLE
                    whiteScreenViewSub.visibility = INVISIBLE
                    logoViewGroup
                        logoViewGroup.beginAnimation() {
                            callBack()

                    }
                }
            }
        })

    }

    var callBack:()->Unit = {}

    fun setOnFinishCallBack(cb:()->Unit){
        callBack = cb
    }
}