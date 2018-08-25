package com.github.sorcererxw.porknife

import com.github.sorcererxw.porknife.entity.Channel
import com.github.sorcererxw.porknife.parser.PodcastParser
import com.github.sorcererxw.porknife.utils.DocumentReader
import org.w3c.dom.Node
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

/**
 * @author: SorcererXW
 * @date: 8/19/2018
 * @description:
 */

class Porknife {
    fun parse(url: String): Channel {
        val document = DocumentReader(url).getDocument()
//        println(document.childNodes.length)
//        DomPrinter(document).printDom()
        val xPath = XPathFactory.newInstance().newXPath()
        val channel = arrayOf("/rss/channel", "/html/body/rss/channel")
                .map {
                    val any = xPath.compile(it).evaluate(document, XPathConstants.NODE)
                    if (any == null) null else any as Node
                }.firstOrNull { it != null } ?: throw Exception("UnSupport $url")

        val podcastParser = PodcastParser(channel)

        return podcastParser.podcast()
    }
}
