package com.ludwig.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import javax.inject.Inject

interface ActivityUtil {
    fun showToast(context: Context, message: CharSequence, duration: Int)
    fun hideKeyboard(activity: Activity)
}

class ActivityUtilImpl @Inject constructor() : ActivityUtil {

    override fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        view?.let { v ->
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }

    override fun showToast(context: Context, message: CharSequence, duration: Int) {
        Toast.makeText(context, message, duration).show()
    }
}
