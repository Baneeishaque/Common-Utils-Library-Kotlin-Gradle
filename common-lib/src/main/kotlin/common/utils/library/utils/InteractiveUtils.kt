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
}