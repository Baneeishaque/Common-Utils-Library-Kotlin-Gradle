package common.utils.library.utils

import common.utils.library.constants.ConstantsCommon
import common.utils.library.models.CommonDataModel
import common.utils.library.models.FailureWithoutExplanationBasedOnIsOkModel
import common.utils.library.models.IsOkModel
import common.utils.library.models.SuccessBasedOnIsOkModel
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

object ApiUtilsInteractiveCommon {

    @JvmStatic
    fun <T> makeApiRequestWithOptionalRetries(

        apiCallFunction: () -> Result<T>,
        isConsoleMode: Boolean,
        isDevelopmentMode: Boolean

    ): IsOkModel<T> {

        if (isConsoleMode) {

            println("Contacting Server...")
        }

        val apiResponse: Result<T> = apiCallFunction.invoke()
        if (isDevelopmentMode) {

            println("API Response : $apiResponse")
        }

        if (apiResponse.isFailure) {

            println("Error : ${(apiResponse.exceptionOrNull() as Exception).localizedMessage}")
            do {
                print("Retry (Y/N) ? : ")
                when (readln()) {
                    "Y", "" -> {

                        return makeApiRequestWithOptionalRetries(

                            apiCallFunction = apiCallFunction,
                            isConsoleMode = isConsoleMode,
                            isDevelopmentMode = isDevelopmentMode
                        )
                    }

                    "N" -> {

                        return FailureWithoutExplanationBasedOnIsOkModel()
                    }

                    else -> println("Invalid option, try again...")
                }
            } while (true)

        } else {

            return SuccessBasedOnIsOkModel(ownData = apiResponse.getOrNull()!!)
        }
    }

    @JvmStatic
    fun <T> getResultFromApiRequestWithOptionalRetries(

        apiCallFunction: () -> Result<T>,
        isConsoleMode: Boolean,
        isDevelopmentMode: Boolean

    ): Result<T> {

        val apiRequestWithOptionalRetriesResult: IsOkModel<T> =
            makeApiRequestWithOptionalRetries(

                apiCallFunction = apiCallFunction,
                isConsoleMode = isConsoleMode,
                isDevelopmentMode = isDevelopmentMode
            )

        return if (apiRequestWithOptionalRetriesResult.isOK) {

            Result.success(value = apiRequestWithOptionalRetriesResult.data!!)

        } else {

            Result.failure(exception = Exception("Please retry the operation..."))
        }
    }

    @JvmStatic
    fun <T> isNoDataResponseWithMessage(

        responseStatus: T,
        noDataIndicator: T,
        noDataActions: () -> Unit = fun() {},
        itemSpecification: String

    ): Boolean = ApiUtilsCommon.isNoDataResponse(

        responseStatus = responseStatus,
        noDataIndicator = noDataIndicator,
        noDataActions = fun() {

            noDataActions.invoke()
            println("No ${itemSpecification}s...")
        })

    @JvmStatic
    fun isNoDataResponseWithMessageAnd1AsIndicator(

        responseStatus: UInt,
        noDataActions: () -> Unit = fun() {},
        itemSpecification: String

    ): Boolean = isNoDataResponseWithMessage(

        responseStatus = responseStatus,
        noDataIndicator = 1u,
        noDataActions = noDataActions,
        itemSpecification = itemSpecification
    )

    @JvmStatic
    fun printServerExecutionErrorMessage(data: String): Unit = ErrorUtilsInteractive.printErrorMessage(

        dataSpecification = "Server Execution Error",
        message = data
    )

    @JvmStatic
    fun printErrorMessageForApi(

        errorMessage: String

    ): Unit = print(

        Json.encodeToString(

            serializer = CommonDataModel.serializer(Unit.serializer()),
            value = CommonDataModel(

                status = 1,
                error = errorMessage
            )
        )
    )

    @JvmStatic
    fun printErrorMessageForApi(): Unit = print(

        Json.encodeToString(

            serializer = CommonDataModel.serializer(Unit.serializer()),
            value = CommonDataModel(

                status = 1
            )
        )
    )

    @JvmStatic
    fun printSuccessMessageWithDataForApi(

        textData: String

    ): Unit = print(

        Json.encodeToString(

            serializer = CommonDataModel.serializer(Unit.serializer()),
            value = CommonDataModel(

                status = 0,
                textData = textData
            )
        )
    )

    @JvmStatic
    fun printSuccessMessageForApi(): Unit = print(

        Json.encodeToString(

            serializer = CommonDataModel.serializer(Unit.serializer()),
            value = CommonDataModel(

                status = 0
            )
        )
    )

    @JvmStatic
    fun printMissingArgumentMessageForApi(

        argumentSummary: String,
        searchedInEnvironmentFile: Boolean = true

    ): Unit = if (searchedInEnvironmentFile) {

        printErrorMessageForApi(

            errorMessage = "Missing $argumentSummary in command line arguments & environment file"
        )

    } else {

        printErrorMessageForApi(

            errorMessage = "Missing $argumentSummary in command line arguments"
        )
    }

    @JvmStatic
    fun printInvalidArgumentValueMessageForApi(

        argumentSummary: String,
        searchedInEnvironmentFile: Boolean = true

    ): Unit = if (searchedInEnvironmentFile) {

        printErrorMessageForApi(

            errorMessage = "Invalid $argumentSummary value in command line arguments & environment file"
        )

    } else {

        printErrorMessageForApi(

            errorMessage = "Invalid $argumentSummary value in command line arguments"
        )
    }

    @JvmStatic
    fun printNegativeNumberOrZeroArgumentValueMessageForApi(

        argumentSummary: String,
        searchedInEnvironmentFile: Boolean = true,
        numberType: String = "Integer"

    ): Unit = if (searchedInEnvironmentFile) {

        printErrorMessageForApi(

            errorMessage = "$argumentSummary Must be Positive $numberType - in command line arguments & environment file"
        )

    } else {

        printErrorMessageForApi(

            errorMessage = "$argumentSummary Must be Positive $numberType - in command line arguments"
        )
    }

    @JvmStatic
    fun printNegativeDoubleArgumentValueMessageForApi(

        argumentSummary: String,
        searchedInEnvironmentFile: Boolean = true

    ): Unit = printNegativeNumberOrZeroArgumentValueMessageForApi(

        argumentSummary = argumentSummary,
        searchedInEnvironmentFile = searchedInEnvironmentFile,
        numberType = "Double"
    )

    @JvmStatic
    fun printNotImplementedMessageForApi(): Unit =
        printErrorMessageForApi(errorMessage = ConstantsCommon.notImplementedMessage)
}
