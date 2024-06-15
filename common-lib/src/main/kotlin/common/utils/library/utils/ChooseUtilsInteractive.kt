package common.utils.library.utils

import common.utils.library.models.ChooseByIdResult
import common.utils.library.models.FailureWithoutExplanationBasedOnIsOkModel
import java.util.*

object ChooseUtilsInteractive {

    @JvmStatic
    fun <T> chooseById(

        itemSpecification: String,
        apiCallFunction: () -> Result<T>,
        prefixForPrompt: String = "",
        isConsoleMode: Boolean,
        isDevelopmentMode: Boolean

    ): ChooseByIdResult<T> {

        var idInput: UInt
        val reader = Scanner(System.`in`)

        while (true) {

            print("Enter $prefixForPrompt$itemSpecification ID or 0 to Back : ")

            try {

                idInput = reader.nextInt().toUInt()
                if (idInput == 0u) {

                    return ChooseByIdResult(

                        isOkWithData = FailureWithoutExplanationBasedOnIsOkModel()
                    )
                }

                return ChooseByIdResult(

                    isOkWithData = ApiUtilsInteractiveCommon.makeApiRequestWithOptionalRetries(

                        apiCallFunction = apiCallFunction,
                        isConsoleMode = isConsoleMode,
                        isDevelopmentMode = isDevelopmentMode
                    ),
                    id = idInput
                )

            } catch (exception: InputMismatchException) {

                println("Invalid $itemSpecification ID...")
            }
        }
    }
}
