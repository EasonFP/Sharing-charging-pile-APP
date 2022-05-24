package com.example.charge.ui.snackTools

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.charge.R
import com.google.android.material.snackbar.Snackbar

object Snack {
    fun onSNACK(rootView: View, toatWord:String){
        val coordinatorLayout = rootView.findViewById<View>(android.R.id.content)
        val snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_SHORT)

        snackbar.view.setPadding(0, 0, 0, 0)
        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
        val snackbarView = snackbar.view as Snackbar.SnackbarLayout
        val layoutParams = snackbarView.layoutParams
        val fl = FrameLayout.LayoutParams(layoutParams.width, layoutParams.height)
        fl.gravity = Gravity.CENTER
        snackbarView.layoutParams = fl
        val inflate: View =
            LayoutInflater.from(snackbar.view.context).inflate(R.layout.snackbar_layout, null)
        val tostText = inflate.findViewById<TextView>(R.id.tostText)
        tostText.text = toatWord
        snackbarView.addView(inflate)
        snackbar.show()
    }
}