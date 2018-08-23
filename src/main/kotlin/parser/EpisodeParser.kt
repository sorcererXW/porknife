package parser

import entity.Episode
import entity.EpisodeEnclosure
import org.w3c.dom.Node
import utils.DatetimeUtil
import utils.RssNamespaceResolver
import java.util.*
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

/**
 * @author: SorcererXW
 * @date: 8/22/2018
 * @description:
 */

class EpisodeParser(private val item: Node) {
    private val xPath = XPathFactory.newInstance().newXPath()
    init {
        xPath.namespaceContext = RssNamespaceResolver()
    }

    fun title(): String = arrayOf("title").map { xPath.compile(it).evaluate(item) }.first()
    fun link(): String = arrayOf("link").map { xPath.compile(it).evaluate(item) }.first()
    fun guid(): String = arrayOf("guid").map { xPath.compile(it).evaluate(item) }.first()
    fun pubData(): Date = arrayOf("pubDate").map { xPath.compile(it).evaluate(item) }.filter { !it.isNullOrEmpty() }.map { DatetimeUtil.pubDateConvert(it) }.first()
    fun author(): String = arrayOf("author", "itunes:author").map { xPath.compile(it).evaluate(item) }.first()
    fun enclosure(): EpisodeEnclosure = arrayOf("enclosure").map { xPath.compile(it).evaluate(item, XPathConstants.NODE) as Node }.map { EnclosureParser(it).enclosure() }.first()
    fun subtitle(): String = arrayOf("itunes:subtitle").map { xPath.compile(it).evaluate(item) }.first()
    fun duration(): Int = arrayOf("itunes:duration").map { xPath.compile(it).evaluate(item) }.filter { !it.isNullOrEmpty() }.map { DatetimeUtil.duration2Second(it) }.first()
    fun image(): String = arrayOf("itunes:image/@href").map { xPath.compile(it).evaluate(item) }.first()
    fun description(): String = arrayOf("description").map { xPath.compile(it).evaluate(item) }.first()
    fun summary(): String = arrayOf("itunes:summary").map { xPath.compile(it).evaluate(item) }.first()
    fun encoded(): String = arrayOf("content:encoded").map { xPath.compile(it).evaluate(item) }.first()
    fun episodeType(): String = arrayOf("episodeType").map { xPath.compile(it).evaluate(item) }.first()
    fun explicit(): Boolean = arrayOf("itunes:explicit").map { xPath.compile(it).evaluate(item) }.map { it == "yes" }.first()
    fun episode(): Episode = Episode(
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
