package common.utils.library.utils

object ErrorUtilsInteractive {

    @JvmStatic
    fun printErrorMessage(

        dataSpecification: String,
        message: String? = null

    ): Unit = println(

        ErrorUtils.constructErrorMessage(

            dataSpecification = dataSpecification,
            message = message
        )
    )

    @JvmStatic
    fun printInvalidOptionMessage(): Unit = println("Invalid option, try again...")
}
