package io.rrohaill.cleanweatherapp.common.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormat {
    const val HH_mm = "HH:mm"
}

fun Long.toDateTime(format: String, zoneId: Int? = null): String {
    val date = Date(this@toDateTime)

    val language = androidx.compose.ui.text.intl.Locale.current.language
    val locale = Locale(language)

    return SimpleDateFormat(format, locale).apply {
        timeZone = TimeZone.getDefault().apply { zoneId?.let { rawOffset = it } }
    }.format(date)
}