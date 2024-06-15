package common.utils.library.utils

object SentenceUtils {

    fun reverseOrderOfWords(sentence: String): String = sentence.split(" ").reversed().joinToString(separator = " ")
}
