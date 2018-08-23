package com.github.sorcererxw.porknife.entity

import java.util.*
import kotlin.collections.ArrayList

/**
 * @author: SorcererXW
 * @date: 8/19/2018
 * @description:
 */

data class Channel(
        val title: String = "",
        val author: String = "",
        val link: String = "",
        val owner: Owner? = null,
        var summary: String = "",
        val description: String = "",
        val type: String = "",
        val subtitle: String = "",
        val pubDate: Date? = null,
        val generator: String = "",
        val language: String = "",
        val image: String = "",
        val explicit: Boolean = false,
        val category: List<String> = ArrayList(),
        val item: List<FeedItem> = ArrayList()
)
