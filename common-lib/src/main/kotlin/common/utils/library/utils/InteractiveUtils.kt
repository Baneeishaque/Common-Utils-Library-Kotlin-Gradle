package common.utils.library.utils

object InteractiveUtils {

    @JvmStatic
    fun printErrorMessage(

        message: String,
        dataSpecification: String? = null,
    ) {

        if (dataSpecification == null) {

            println(message)

        } else {
            println(
                constructErrorMessage(

                    dataSpecification = dataSpecification,
                    message = message
                )
            )
        }
    }

    @JvmStatic
    fun constructErrorMessage(dataSpecification: String, message: String): String =
        "$dataSpecification Error : $message"

    @JvmStatic
    fun invalidOptionMessage() {

        println("Invalid option, try again...")
    }

    @JvmStatic
    fun generateInvalidInputMessage(inputSpecifier: String): String {

        return "No Valid \"$inputSpecifier\" Provided by User"
    }
}
