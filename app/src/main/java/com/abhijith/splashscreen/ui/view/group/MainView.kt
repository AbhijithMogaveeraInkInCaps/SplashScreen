package com.abhijith.splashscreen.ui.view.group

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.abhijith.splashscreen.ui.view.WhiteScreenViewLTR

class MainView : ViewGroup {

    val logoViewGroup: LogoView = LogoView(context)

    private val bannerView: BottomBanner = BottomBanner(context)
    private val whiteScreenViewMain = WhiteScreenViewLTR(context, true)

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
        logoViewGroup.layout(0, 0, width, height)
        bannerView.layout(0,height/2,width,(height/2)+200)
        whiteScreenViewMain.layout(0, 0, width, height)
        addView(logoViewGroup)
        addView(bannerView)
        addView(whiteScreenViewMain.also {
            it.beginAnimation(width) {
                bannerView.beginAnimation {
                    bannerView.visibility = INVISIBLE
                    logoViewGroup
                    logoViewGroup.beginAnimation() {
                            callBack()
                    }
                }
            }
        })
    }
    var callBack: () -> Unit = {}

    fun setOnFinishCallBack(cb: () -> Unit) {
        callBack = cb
    }
}