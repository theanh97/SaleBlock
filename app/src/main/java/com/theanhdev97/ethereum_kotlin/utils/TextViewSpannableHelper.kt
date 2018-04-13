package com.theanhdev97.ethereum_kotlin.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView

/**
 * Created by DELL on 09/04/2018.
 */
object TextViewSpannableHelper {
    fun span(textView: TextView, content: String, color: Int, from: Int, to: Int) {
        var spannableString = SpannableString(content)
        spannableString.setSpan(ForegroundColorSpan(color), from, to, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.setText(spannableString)
    }
}