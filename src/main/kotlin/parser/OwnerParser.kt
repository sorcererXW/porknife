package parser

import entity.PodcastOwner
import org.w3c.dom.Node
import utils.RssNamespaceResolver
import javax.xml.xpath.XPathFactory

/**
 * @author: SorcererXW
 * @date: 8/23/2018
 * @description:
 */
class OwnerParser(private val owner: Node) {
    private val xPath = XPathFactory.newInstance().newXPath()
    init {
        xPath.namespaceContext = RssNamespaceResolver()
    }

    fun name(): String = xPath.compile("itunes:name").evaluate(owner)
    fun email(): String = xPath.compile("itunes:email").evaluate(owner)
    fun owner():PodcastOwner = PodcastOwner(name = name(), email = email())
}
