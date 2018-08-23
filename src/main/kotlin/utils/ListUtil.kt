package utils

/**
 * @author: SorcererXW
 * @date: 8/22/2018
 * @description:
 */
object ListUtil {
    fun <E, T> convertToList(target: E, length: Int, indexFun: (E, index: Int) -> T): List<T> {
        val list = ArrayList<T>()
        for (i in 0 until length) {
            list.add(indexFun(target, i))
        }
        return list
    }
}
