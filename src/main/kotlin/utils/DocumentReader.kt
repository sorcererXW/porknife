package utils

import org.jsoup.Jsoup
import org.jsoup.helper.W3CDom
import org.w3c.dom.Document

/**
 * @author: SorcererXW
 * @date: 8/21/2018
 * @description:
 */

class DocumentReader(private val url: String) {
    companion object {
        private const val USER_AGENT = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2"
    }

    fun getDocument(): Document {
        val jsoupDocument = Jsoup.connect(url).header("User-Agent", USER_AGENT).get()
        return W3CDom().fromJsoup(jsoupDocument)
    }
}
