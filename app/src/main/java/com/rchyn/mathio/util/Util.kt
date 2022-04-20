package com.rchyn.mathio.util

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

object Util {

    fun setHideSoftKeyboard(
        context: FragmentActivity
    ) {
        val inputMethodManager =
            context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(context.currentFocus?.windowToken, 0)
    }
}