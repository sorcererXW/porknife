package entity

import java.util.*

/**
 * @author: SorcererXW
 * @date: 8/19/2018
 * @description:
 */
data class Episode(
        val title: String,
        val link: String,
        val guid: String,
        val pubData: Date,
        val author: String,
        val episodeType: String,
        val subtitle: String,
        val duration: Int,
        val explicit: Boolean,
        val image: String
)
