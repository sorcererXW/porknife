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
        val category: List<String> = ArrayList(),
        val item: List<FeedItem> = ArrayList()
) {
    override fun toString(): String {
        return "Podcast(" +
                "\ntitle='$title'," +
                "\nauthor='$author'," +
                "\nlink='$link'," +
                "\nowner=$owner," +
                "\nsummary='$summary'," +
                "\ndescription='$description'," +
                "\ntype='$type'," +
                "\nsubtitle='$subtitle'," +
                "\npubDate=$pubDate," +
                "\ngenerator='$generator'," +
                "\nlanguage='$language'," +
                "\nimage='$image'," +
                "\ncategory=$category," +
                "\nitem=$item" +
                ")"
    }
}
