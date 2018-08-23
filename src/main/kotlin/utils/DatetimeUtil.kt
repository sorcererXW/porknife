package utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author: SorcererXW
 * @date: 8/22/2018
 * @description:
 */

object DatetimeUtil {
    fun pubDateConvert(it: String): Date =
            SimpleDateFormat("EE, dd MMM yy hh:mm:ss Z").parse(it)

    fun duration2Second(it: String): Int {
        val dateFormat = SimpleDateFormat("mm:ss")
        val base = dateFormat.parse("00:00")
        val date = dateFormat.parse(it)
        return ((date.time - base.time) / 1000L).toInt()
    }
}
