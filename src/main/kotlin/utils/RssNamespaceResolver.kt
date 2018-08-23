package utils

import javax.xml.namespace.NamespaceContext

/**
 * @author: SorcererXW
 * @date: 8/23/2018
 * @description:
 */
class RssNamespaceResolver : NamespaceContext {
    private val nsMap = hashMapOf(
            "itunes" to "http://www.itunes.com/dtds/podcast-1.0.dtd",
            "content" to "http://purl.org/rss/1.0/modules/content/"
    )

    override fun getNamespaceURI(prefix: String): String? {
        return when {
            nsMap.contains(prefix) -> nsMap[prefix]
            else -> null
        }
    }

    override fun getPrefix(namespaceURI: String?): String {
        return if (nsMap.containsValue(namespaceURI)) {
            nsMap.entries.filter { it.value == namespaceURI }.map { it.key }.first()
        } else {
            ""
        }
    }

    override fun getPrefixes(namespaceURI: String?): MutableIterator<Any?>? {
        return null
    }
}
