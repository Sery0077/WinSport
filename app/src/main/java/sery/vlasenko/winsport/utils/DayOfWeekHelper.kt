package sery.vlasenko.winsport.utils

import java.text.SimpleDateFormat
import java.util.*

object DayOfWeekHelper {
    private val calendar: Calendar = Calendar.getInstance()

    private val sdfEN = SimpleDateFormat("EEEE", Locale.ENGLISH)
    private val sdfRU = SimpleDateFormat("EEEE", Locale.forLanguageTag("ru"))


    fun getDayOfWeekRU(): String {
        val date = calendar.time
        return sdfRU.format(date.time).lowercase()
    }

    fun getDayOfWeekEN(): String {
        val date = calendar.time
        return sdfEN.format(date.time).lowercase()
    }
}