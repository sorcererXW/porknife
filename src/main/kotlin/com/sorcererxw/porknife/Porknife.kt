package com.sorcererxw.porknife

import com.sorcererxw.porknife.entity.Podcast
import org.w3c.dom.Node
import com.sorcererxw.porknife.parser.PodcastParser
import com.sorcererxw.porknife.utils.DocumentReader
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

/**
 * @author: SorcererXW
 * @date: 8/19/2018
 * @description:
 */

class Porknife {
    fun parse(url: String): Podcast {
        val document = DocumentReader(url).getDocument()

        val xPath = XPathFactory.newInstance().newXPath()
        val channel = xPath.compile("/rss/channel")
                .evaluate(document, XPathConstants.NODE) as Node

        val podcastParser = PodcastParser(channel)

        return podcastParser.podcast()
    }
}
