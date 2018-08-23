package com.github.sorcererxw.porknife.entity

import java.util.*

/**
 * @author: SorcererXW
 * @date: 8/19/2018
 * @description:
 */
data class FeedItem(
        val title: String = "",
        val link: String = "",
        val guid: String = "",
        val pubData: Date? = null,
        val author: String = "",
        val episodeType: String = "",
        val subtitle: String = "",
        val duration: Int = 0,
        val explicit: Boolean = false,
        val image: String = "",
        val enclosure: Enclosure? = null,
        val description: String = "",
        val summary: String = "",
        val encoded: String = ""
)
