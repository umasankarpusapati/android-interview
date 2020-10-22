package com.png.interview.databinding

import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.Gravity
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.png.interview.extensions.NoUnderlineURLSpan

object CommonBindingAdapter {
    @BindingAdapter("visible")
    @JvmStatic
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) VISIBLE else GONE
    }

    @BindingAdapter("renderHtml")
    @JvmStatic
    fun renderHtml(view: TextView, description: String) {
        val descriptionSpan: Spannable = SpannableString(HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT))
        val spans = descriptionSpan.getSpans(0, descriptionSpan.length, URLSpan::class.java)
        for (span in spans) {
            val start = descriptionSpan.getSpanStart(span)
            val end = descriptionSpan.getSpanEnd(span)
            descriptionSpan.removeSpan(span)
            descriptionSpan.setSpan(NoUnderlineURLSpan(span.url), start, end, 0)
        }
        view.text = descriptionSpan
        view.movementMethod = LinkMovementMethod.getInstance()
    }

    @JvmStatic
    @BindingAdapter("isStartGravity")
    fun setGravityStartAligned(view: TextView, isStartAligned: Boolean) {
        view.gravity = if (isStartAligned) {
            Gravity.START
        } else {
            Gravity.CENTER
        }
    }
}
