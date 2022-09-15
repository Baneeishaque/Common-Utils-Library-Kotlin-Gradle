package common.utils.library.utils

object SentenceUtils {

    fun reverseOrderOfWords(sentence: String) = sentence.split(" ").reversed().joinToString(" ")
}