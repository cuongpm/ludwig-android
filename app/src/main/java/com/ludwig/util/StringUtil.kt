package com.ludwig.util

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.ludwig.R
import com.ludwig.widget.ColoredUnderlineSpan


/**
 * Created by cuongpm on 2019-05-19.
 */

object StringUtil {

    fun highlightKeyword(
        context: Context,
        content: String,
        keyword: String,
        colorId: Int,
        isBold: Boolean,
        isUnderLine: Boolean
    ): SpannableString {
        val start = content.indexOf(keyword)
        return SpannableString(content).apply {
            if (isBold) {
                setSpan(StyleSpan(Typeface.BOLD), start, start + keyword.length, 0)
            }
            if (isUnderLine) {
                val color = ContextCompat.getColor(context, colorId)
                val thickness = context.resources.getDimensionPixelOffset(R.dimen.underline_thickness).toFloat()
                setSpan(ColoredUnderlineSpan(color, thickness), start, start + keyword.length, 0)
            }
        }
    }

    fun highlightMultipleKeywords(
        context: Context,
        content: String,
        keywords: List<String>,
        colorIds: List<Int>,
        isBold: Boolean,
        isUnderLine: Boolean
    ): SpannableString {
        val spannableString = SpannableString(content)
        keywords.forEachIndexed { index, keyword ->
            val start = content.indexOf(keyword)
            spannableString.apply {
                if (isBold) {
                    setSpan(StyleSpan(Typeface.BOLD), start, start + keyword.length, 0)
                }
                if (isUnderLine) {
                    val colorId = if (colorIds.size == keywords.size) colorIds[index] else colorIds[0]
                    val color = ContextCompat.getColor(context, colorId)
                    val thickness = context.resources.getDimensionPixelOffset(R.dimen.underline_thickness).toFloat()
                    setSpan(ColoredUnderlineSpan(color, thickness), start, start + keyword.length, 0)
                }
            }
        }
        return spannableString
    }
}
