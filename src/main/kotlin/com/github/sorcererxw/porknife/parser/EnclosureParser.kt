package com.github.sorcererxw.porknife.parser

import com.github.sorcererxw.porknife.entity.Enclosure
import com.github.sorcererxw.porknife.utils.RssNamespaceResolver
import org.w3c.dom.Node
import javax.xml.xpath.XPathFactory

/**
 * @author: SorcererXW
 * @date: 8/22/2018
 * @description:
 */

class EnclosureParser(private val enclosure: Node) {
    private val xPath = XPathFactory.newInstance().newXPath()

    init {
        xPath.namespaceContext = RssNamespaceResolver()
    }

    fun url(): String = xPath.compile("@url").evaluate(enclosure)
    fun length(): Long = xPath.compile("@length").evaluate(enclosure).toLongOrNull() ?: 0
    fun type(): String = xPath.compile("@type").evaluate(enclosure)
    fun enclosure(): Enclosure = Enclosure(
            url = url(),
            length = length(),
            type = type()
    )
}
