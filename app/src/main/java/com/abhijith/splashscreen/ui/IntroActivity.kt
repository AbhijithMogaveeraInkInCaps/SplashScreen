package com.abhijith.splashscreen.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import com.abhijith.splashscreen.R

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.experimental)
        findViewById<MotionLayout>(R.id.root).apply {

            addTransitionListener(object : MotionLayout.TransitionListener {

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

                }

                override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                    if (R.id.step1 == p1) {
                        Log.e("Transition1 to","2")
                        setTransition(R.id.step1, R.id.step2)
                        transitionToEnd()
                    }
                    if (R.id.step2 == p1) {
                        Log.e("Transition2 to ","3")
                        setTransition(R.id.step2, R.id.step3)
                        transitionToEnd()
                    }
                    if (R.id.step3 == p1) {
                        Log.e("Transition3 to ","4")
                        setTransition(R.id.step3, R.id.step4)
                        transitionToEnd()
                    }
                    if (R.id.step4 == p1) {
                        Log.e("Transition4 to ","5")
                        setTransition(R.id.step4, R.id.step5)
                        transitionToEnd()
                    }

                }

                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {

                }
            })

            setTransition(R.id.start, R.id.step1)
            transitionToEnd()

        }
    }
}