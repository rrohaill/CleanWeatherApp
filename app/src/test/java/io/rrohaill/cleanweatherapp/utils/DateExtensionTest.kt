package io.rrohaill.cleanweatherapp.utils

import io.rrohaill.cleanweatherapp.common.utils.DateFormat
import io.rrohaill.cleanweatherapp.common.utils.toDateTime
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DateExtensionTest {

    @Test
    fun getDateConversion() {
        val date = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 6)
            set(Calendar.MINUTE, 10)
        }.time.time

        assertEquals(date.toDateTime(DateFormat.HH_mm), "06:10")
    }
}