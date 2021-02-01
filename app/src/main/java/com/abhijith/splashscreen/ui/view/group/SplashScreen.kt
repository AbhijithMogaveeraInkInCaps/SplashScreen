package com.abhijith.splashscreen.ui.view.group

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.abhijith.splashscreen.ui.view.WhiteScreenViewLTR

class SplashScreen : ViewGroup {

    private val logoView: LogoView = LogoView(context)
    private val bannerView: BottomBanner = BottomBanner(context)
    private val animationWhiteScreen = WhiteScreenViewLTR(context)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?)
            : super(context, attrs)

    constructor(context: Context?)
            : super(context) {
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        removeAllViews()
        logoView.layout(0, 0, width, height)
        bannerView.layout(0,height/2,width,(height/2)+200)
        animationWhiteScreen.layout(0, 0, width, height)
        addView(logoView)
        addView(bannerView)
        addView(animationWhiteScreen.also {
            it.beginAnimation(2000L, {
            },{
                bannerView.beginAnimation {
                    bannerView.visibility = INVISIBLE
                    logoView.beginAnimation(1800, {
                    },{
                        callBack()
                    })
                }
            })
        })
    }
    var callBack: () -> Unit = {}

    fun setOnFinishCallBack(cb: () -> Unit) {
        callBack = cb
    }
}