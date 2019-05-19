package com.ludwig.util

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.core.content.ContextCompat
import com.ludwig.R


/**
 * Created by cuongpm on 2019-05-19.
 */

object StringUtil {

    fun highlightKeyword(context: Context, content: String, keyword: String, colorId: Int): SpannableString {
        val start = content.indexOf(keyword)
        return SpannableString(content).apply {
            setSpan(StyleSpan(Typeface.BOLD), start, start + keyword.length, 0)
            val underlineSpan = UnderlineSpan().updateDrawState(TextPaint().apply {
                color = ContextCompat.getColor(context, R.color.colorPrimary)
            })
            setSpan(underlineSpan, start, start + keyword.length, 0)
        }
    }
}