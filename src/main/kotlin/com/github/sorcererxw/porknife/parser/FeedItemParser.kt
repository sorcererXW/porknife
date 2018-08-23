package com.github.sorcererxw.porknife.parser

import com.github.sorcererxw.porknife.entity.FeedItem
import com.github.sorcererxw.porknife.entity.Enclosure
import com.github.sorcererxw.porknife.utils.DatetimeUtil
import com.github.sorcererxw.porknife.utils.RssNamespaceResolver
import org.w3c.dom.Node
import java.util.*
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

/**
 * @author: SorcererXW
 * @date: 8/22/2018
 * @description:
 */

class FeedItemParser(private val item: Node) {
    private val xPath = XPathFactory.newInstance().newXPath()

    init {
        xPath.namespaceContext = RssNamespaceResolver()
    }

    fun title(): String = arrayOf("title").map { xPath.compile(it).evaluate(item) }.first()
    fun link(): String = arrayOf("link").map { xPath.compile(it).evaluate(item) }.first()
    fun guid(): String = arrayOf("guid").map { xPath.compile(it).evaluate(item) }.first()
    fun pubData(): Date = arrayOf("pubDate").map { xPath.compile(it).evaluate(item) }.filter { !it.isNullOrEmpty() }.map { DatetimeUtil.pubDateConvert(it) }.first()
    fun author(): String = arrayOf("author", "itunes:author").map { xPath.compile(it).evaluate(item) }.first()
    fun enclosure(): Enclosure = arrayOf("enclosure").map { xPath.compile(it).evaluate(item, XPathConstants.NODE) as Node }.map { EnclosureParser(it).enclosure() }.first()
    fun subtitle(): String = arrayOf("itunes:subtitle").map { xPath.compile(it).evaluate(item) }.first()
    fun image(): String = arrayOf("itunes:image/@href").map { xPath.compile(it).evaluate(item) }.first()
    fun description(): String = arrayOf("description").map { xPath.compile(it).evaluate(item) }.first()
    fun summary(): String = arrayOf("itunes:summary").map { xPath.compile(it).evaluate(item) }.first()
    fun encoded(): String = arrayOf("content:encoded").map { xPath.compile(it).evaluate(item) }.first()
    fun episodeType(): String = arrayOf("episodeType").map { xPath.compile(it).evaluate(item) }.first()
    fun explicit(): Boolean = arrayOf("itunes:explicit").map { xPath.compile(it).evaluate(item) }.map { it == "yes" }.first()
    fun duration(): Int = arrayOf("itunes:duration").map { xPath.compile(it).evaluate(item) }
            .filter { !it.isNullOrEmpty() }.map { DatetimeUtil.duration2Second(it) }
            .firstOrNull() ?: 0

    fun episode(): FeedItem = FeedItem(
            title = title(),
            link = link(),
            guid = guid(),
            pubData = pubData(),
            author = author(),
            enclosure = enclosure(),
            subtitle = subtitle(),
            duration = duration(),
            image = image(),
            description = description(),
            summary = summary(),
            encoded = encoded(),
            episodeType = episodeType(),
            explicit = explicit()
    )
}
