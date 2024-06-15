package common.utils.library.utils

object InputUtils {

    @JvmStatic
    fun getValidFloat(

        inputText: String

    ): Pair<Boolean, Float?> = try {

        Pair(true, inputText.toFloat())

    } catch (exception: NumberFormatException) {

        Pair(false, null)
    }
}
