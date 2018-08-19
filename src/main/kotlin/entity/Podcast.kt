package entity

import java.util.*

/**
 * @author: SorcererXW
 * @date: 8/19/2018
 * @description:
 */

data class Podcast(
        val title: String,
        val author: String,
        val link: String,
        val owner: PodcastOwner,
        var summary: String,
        val description: String,
        val type: String,
        val subtitle: String,
        val pubDate: Date,
        val generator: String,
        val language: String,
        val image: String,
        val categoies: Array<String>
)
