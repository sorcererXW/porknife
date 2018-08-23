package com.sorcererxw.porknife.parser

import com.sorcererxw.porknife.entity.Episode
import com.sorcererxw.porknife.entity.Podcast
import com.sorcererxw.porknife.entity.PodcastOwner
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import com.sorcererxw.porknife.utils.DatetimeUtil
import com.sorcererxw.porknife.utils.ListUtil
import com.sorcererxw.porknife.utils.RssNamespaceResolver
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
    fun image(): String = arrayOf("image").map { xPath.compile(it).evaluate(channel) }.first()

    fun owner(): PodcastOwner? = arrayOf("itunes:owner")
            .map { xPath.compile(it).evaluate(channel, XPathConstants.NODE) as Node }
            .map { OwnerParser(it).owner() }.firstOrNull()

    fun pubDate(): Date = arrayOf("pubDate", "lastBuildDate")
            .map { xPath.compile(it).evaluate(channel) }
            .filter { !it.isNullOrEmpty() }.map { DatetimeUtil.pubDateConvert(it) }.first()

    fun category(): List<String> = arrayOf("itunes:category/@text")
            .map { xPath.compile(it).evaluate(channel, XPathConstants.NODESET) as NodeList }
            .map { ListUtil.convertToList(it, it.length) { set, idx -> set.item(idx) } }
            .flatMap { it }.map { it.nodeValue }.toList()

    fun items(): List<Episode> = arrayOf("item")
            .map { xPath.compile(it).evaluate(channel, XPathConstants.NODESET) as NodeList }
            .map { ListUtil.convertToList(it, it.length) { set, idx -> set.item(idx) } }
            .flatMap { it }.map { EpisodeParser(it).episode() }
            .toList()

    fun podcast(): Podcast = Podcast(
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
            item = items()
    )
}
