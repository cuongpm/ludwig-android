package com.ludwig.widget

import android.text.TextPaint
import android.text.style.CharacterStyle
import android.text.style.UpdateAppearance


class ColoredUnderlineSpan(
    private val color: Int,
    private val thickness: Float
) : CharacterStyle(), UpdateAppearance {

    override fun updateDrawState(tp: TextPaint) {
        try {
            val method = TextPaint::class.java.getMethod(
                "setUnderlineText",
                Integer.TYPE,
                java.lang.Float.TYPE
            )
            method.invoke(tp, color, thickness)
        } catch (exception: Exception) {
            tp.isUnderlineText = true
        }
    }
}
