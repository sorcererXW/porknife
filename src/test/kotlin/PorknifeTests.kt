import com.github.sorcererxw.porknife.Porknife
import com.github.sorcererxw.porknife.utils.DocumentReader
import com.github.sorcererxw.porknife.utils.DomPrinter
import java.text.SimpleDateFormat
import java.util.*
import javax.xml.xpath.XPathFactory

/**
 * @author: SorcererXW
 * @date: 8/21/2018
 * @description:
 */

class PorknifeTests {

}

fun testParser() {
    val url = "http://yitianshijie.net/rss"

    var porknife = Porknife()
    var podcast = porknife.parse(url)

    println(podcast.toString())
}


fun testDomPrinter() {
    val url = "http://yitianshijie.net/rss"
    DomPrinter(DocumentReader(url).getDocument().firstChild).printDom()
}

fun testDateFormat() {
    val format = "EE, dd MMM yy hh:mm:ss Z"
    val dateString = "Thu, 17 May 2018 01:00:00 -0700"
    val simpleDateFormat = SimpleDateFormat(format)
    println(simpleDateFormat.format(Date()))
    println(simpleDateFormat.parse(dateString).time)
}

fun testXpath() {
    val url = "http://yitianshijie.net/rss"

    val document = DocumentReader(url).getDocument()


    val xPathFactory = XPathFactory.newInstance()
    val xPath = xPathFactory.newXPath()

    val node = xPath.compile("rss/channel/itunes:author").evaluate(document)

    println(node)
}

fun main(args: Array<String>) {
//    testDateFormat()
//    testDomPrinter()
    testParser()
//    testXpath()
}
