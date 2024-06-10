package common.utils.library.utils

object InteractiveUtils {

    @JvmStatic
    fun printErrorMessage(
        data: String,
        dateSpecification: String
    ) {

        println("$dateSpecification Error : $data")
    }

    @JvmStatic
    fun invalidOptionMessage() {

        println("Invalid option, try again...")
    }

    @JvmStatic
    fun generateInvalidInputMessage(inputSpecifier: String): String {

        return "No Valid \"$inputSpecifier\" Provided by User"
    }
}
