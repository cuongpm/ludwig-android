package com.ludwig.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.ludwig.R
import javax.inject.Inject


interface DialogUtil {
    fun showSimpleProgressBar(context: Context)
    fun closeSimpleProgressBar()
}

class DialogUtilImpl @Inject constructor() : DialogUtil {

    private var simpleProgressDialog: Dialog? = null

    private val isShowingProgress: Boolean
        get() = simpleProgressDialog?.isShowing ?: false

    override fun showSimpleProgressBar(context: Context) {
        simpleProgressDialog?.let {
            closeSimpleProgressBar()
        } ?: run {
            simpleProgressDialog = Dialog(context).apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                setContentView(R.layout.dialog_progress_simple)
                window?.setBackgroundDrawable(ColorDrawable(Color.WHITE).apply { alpha = 0 })
                setCancelable(false)
                show()
            }
        }
    }

    override fun closeSimpleProgressBar() {
        if (isShowingProgress) {
            simpleProgressDialog?.cancel()
            simpleProgressDialog = null
        }
    }
}
