package com.smish.butler.util.extensions

import android.content.res.Resources
import android.text.Spanned
import android.text.SpannedString
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat

fun Spanned.toHtmlWithoutParagraphs(): String {
    return HtmlCompat.toHtml(this, HtmlCompat.TO_HTML_PARAGRAPH_LINES_CONSECUTIVE)
        .substringAfter("<p dir=\"ltr\">").substringBeforeLast("</p>")
}

fun Resources.getFormattedText(@StringRes id: Int, vararg args: Any): CharSequence {
    val escapedArgs = args.map {
        if (it is Spanned) it.toHtmlWithoutParagraphs() else it
    }.toTypedArray()
    val resource = SpannedString(getText(id))
    val htmlResource = resource.toHtmlWithoutParagraphs()
    val formattedHtml = String.format(htmlResource, *escapedArgs)
    return HtmlCompat.fromHtml(formattedHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)
}