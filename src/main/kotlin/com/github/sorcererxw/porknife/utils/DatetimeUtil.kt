package com.github.sorcererxw.porknife.utils

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
        val splits = it.split(":")
        var count = 0
        var k = 0
        for (i in splits.size - 1 downTo 0) {
            val value = splits[i].toIntOrNull() ?: return 0
            count += if (k == 0) {
                value
            } else if (k == 1) {
                value * 60
            } else if (k == 2) {
                value * 60 * 60
            } else {
                break
            }
            k++
        }
        return count
    }
}
