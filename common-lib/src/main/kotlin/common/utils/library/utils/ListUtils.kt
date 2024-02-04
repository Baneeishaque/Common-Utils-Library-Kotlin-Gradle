package common.utils.library.utils

object ListUtils {

    @JvmStatic
    fun indexedListTextFromList(list: List<*>): String {

        var result = ""
        list.forEachIndexed { index: Int, listElement: Any? ->
            result += "Option }${index + 1} - ${
                
                listElement.toString().trim()
            }\n"
        }
        return result
    }
}