package com.png.interview.extensions

import android.text.TextPaint
import android.text.style.URLSpan

class NoUnderlineURLSpan(url: String?) : URLSpan(url) {
    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = false
    }
}