package com.github.sorcererxw.porknife.parser

import com.github.sorcererxw.porknife.entity.Channel
import com.github.sorcererxw.porknife.entity.FeedItem
import com.github.sorcererxw.porknife.entity.Owner
import com.github.sorcererxw.porknife.utils.DatetimeUtil
import com.github.sorcererxw.porknife.utils.ListUtil
import com.github.sorcererxw.porknife.utils.RssNamespaceResolver
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.util.*
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

/**
 * @author: SorcererXW
 * @date: 8/20/2018
 * @description:
 */
class PodcastParser(private val channel: Node) {
    private val xPath = XPathFactory.newInstance().newXPath()

    init {
        xPath.namespaceContext = RssNamespaceResolver()
    }

    fun title(): String = arrayOf("title").map { xPath.compile(it).evaluate(channel) }.first()
    fun author(): String = arrayOf("itunes:author").map { xPath.compile(it).evaluate(channel) }.first()
    fun link(): String = arrayOf("link").map { xPath.compile(it).evaluate(channel) }.first()
    fun summary(): String = arrayOf("itunes:summary").map { xPath.compile(it).evaluate(channel) }.first()
    fun description(): String = arrayOf("description").map { xPath.compile(it).evaluate(channel) }.first()
    fun type(): String = arrayOf("itunes:type").map { xPath.compile(it).evaluate(channel) }.first()
    fun subtitle(): String = arrayOf("itunes:subtitle").map { xPath.compile(it).evaluate(channel) }.first()
    fun generator(): String = arrayOf("generator").map { xPath.compile(it).evaluate(channel) }.first()
    fun language(): String = arrayOf("language").map { xPath.compile(it).evaluate(channel) }.first()
    fun image(): String = arrayOf("itunes:image/@href", "image/@href").map { xPath.compile(it).evaluate(channel) }.first()
    fun explicit(): Boolean = arrayOf("itunes:explicit").map { xPath.compile(it).evaluate(channel) }.map { it == "yes" }.first()

    fun owner(): Owner? = arrayOf("itunes:owner")
            .map { xPath.compile(it).evaluate(channel, XPathConstants.NODE) }
            .filter { it != null }.map { it as Node }
            .map { OwnerParser(it).owner() }.firstOrNull()

    fun pubDate(): Date? = arrayOf("pubDate", "lastBuildDate")
            .map { xPath.compile(it).evaluate(channel) }
            .filter { !it.isNullOrEmpty() }.map { DatetimeUtil.pubDateConvert(it) }.firstOrNull()

    fun category(): List<String> = arrayOf("itunes:category/@text")
            .map { xPath.compile(it).evaluate(channel, XPathConstants.NODESET) as NodeList }
            .map { ListUtil.convertToList(it, it.length) { set, idx -> set.item(idx) } }
            .flatMap { it }.map { it.nodeValue }.toList()

    fun items(): List<FeedItem> = arrayOf("item")
            .map { xPath.compile(it).evaluate(channel, XPathConstants.NODESET) as NodeList }
            .map { ListUtil.convertToList(it, it.length) { set, idx -> set.item(idx) } }
            .flatMap { it }.map { FeedItemParser(it).episode() }
            .toList()

    fun podcast(): Channel = Channel(
            title = title(),
            author = author(),
            link = link(),
            owner = owner(),
            summary = summary(),
            description = description(),
            type = type(),
            subtitle = subtitle(),
            pubDate = pubDate(),
            generator = generator(),
            language = language(),
            image = image(),
            category = category(),
            item = items(),
            explicit = explicit()
    )
}
