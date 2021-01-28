package com.abhijith.splashscreen.ui.view

import android.graphics.Color
import android.util.Log

private val color1 = Color.parseColor("#ffafbd")
private val color2 = Color.parseColor("#2193b0")
private val color3 = Color.parseColor("#cc2b5e")
private val color4 = Color.parseColor("#ee9ca7")
private val color5 = Color.parseColor("#42275a")
private val color6 = Color.parseColor("#bdc3c7")
private val color7 = Color.parseColor("#de6262")
private val color8 = Color.parseColor("#06beb6")
private val color9 = Color.parseColor("#eb3349")
val colorList = listOf(color1, color2, color3, color4, color5, color6, color7, color8, color9)
private var cp:Int=0
fun getRandomColor():Int {
    if(cp>8)
        cp=1
    val i = colorList[cp]
    cp++
    return i
}
fun logMsg(tag:String,str:String){
    Log.e(tag,str)
}