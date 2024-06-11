package common.utils.library.utils

object DateTimeInteractiveUtils {

    @JvmStatic
    fun printDateErrorMessage(message: String): Unit = InteractiveUtils.printErrorMessage(

        message = DateTimeUtils.constructDateErrorMessage(message = message)
    )
}
