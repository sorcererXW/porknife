package utils

import org.w3c.dom.Node

/**
 * @author: SorcererXW
 * @date: 8/21/2018
 * @description:
 */

class DomPrinter(private val domElement: Node) {

    private fun printIndent(level: Int) {
        for (i in 0 until level) {
            print("  ")
        }
    }

    private fun printDom(node: Node, level: Int) {
        if (node.nodeType == Node.ELEMENT_NODE) {
            printIndent(level)
            println("<" + node.nodeName + ">")
            if (node.hasChildNodes()) {
                for (i in 0 until node.childNodes.length) {
                    val child = node.childNodes.item(i)
                    printDom(child, level + 1)
                }
            }
            printIndent(level)
            println("<" + node.nodeName + "/>")
        } else if (node.nodeType == Node.TEXT_NODE) {
            val content = node.textContent
            if (content == null || content.trim().isEmpty()) {
                print("")
            } else {
                printIndent(level)
                println(content)
            }
        }
    }

    fun printDom() {
        printDom(domElement, 0)
    }
}
