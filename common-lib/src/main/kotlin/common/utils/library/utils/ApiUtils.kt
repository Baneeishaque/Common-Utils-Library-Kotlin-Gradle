package common.utils.library.utils

import common.utils.library.models.CommonDataModel
import common.utils.library.models.IsOkModel
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

object ApiUtils {

    @JvmStatic
    fun getServerApiMethodAbsoluteUrl(serverApiMethodName: String, serverFileExtension: String): String {

        return "$serverApiMethodName.$serverFileExtension"
    }

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
                when (readLine()!!) {
                    "Y", "" -> {
                        return makeApiRequestWithOptionalRetries(

                            apiCallFunction = apiCallFunction,
                            isConsoleMode = isConsoleMode,
                            isDevelopmentMode = isDevelopmentMode
                        )
                    }

                    "N" -> {
                        return IsOkModel(isOK = false)
                    }

                    else -> println("Invalid option, try again...")
                }
            } while (true)

        } else {

            return IsOkModel(isOK = true, data = apiResponse.getOrNull()!!)
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
    fun <T> isNoDataResponse(

        responseStatus: T,
        noDataIndicator: T,
        noDataActions: () -> Unit

    ): Boolean {

        if (responseStatus == noDataIndicator) {

            noDataActions.invoke()
            return true
        }
        return false
    }

    @JvmStatic
    fun <T> isNoDataResponseWithMessage(

        responseStatus: T,
        noDataIndicator: T,
        noDataActions: () -> Unit = fun() {},
        itemSpecification: String

    ): Boolean {

        return isNoDataResponse(

            responseStatus = responseStatus,
            noDataIndicator = noDataIndicator,
            noDataActions = fun() {

                noDataActions.invoke()
                println("No ${itemSpecification}s...")
            })
    }

    @JvmStatic
    fun isNoDataResponseWithMessageIncludingBeforeMessageActionsAnd1AsIndicator(

        responseStatus: UInt,
        noDataMessageBeforeActions: () -> Unit = fun() {},
        itemSpecification: String

    ): Boolean {

        return isNoDataResponse(

            responseStatus = responseStatus,
            noDataIndicator = 1u,
            noDataActions = fun() {

                noDataMessageBeforeActions.invoke()
                println("No ${itemSpecification}s...")
            })
    }

    @JvmStatic
    fun <T> apiResponseHandler(

        apiResponse: Result<T>,
        apiFailureActions: () -> Unit = fun() {},
        apiSuccessActions: () -> Unit

    ) {

        if (apiResponse.isFailure) {

            apiFailureActions.invoke()

        } else {

            apiSuccessActions.invoke()
        }
    }

    @JvmStatic
    fun printServerExecutionErrorMessage(data: String) {

        InteractiveUtils.printErrorMessage(

            data = data,
            dateSpecification = "Server Execution Error"
        )
    }

    @JvmStatic
    fun printErrorMessageForApi(
        errorMessage: String
    ) {
        print(
            Json.encodeToString(
                serializer = CommonDataModel.serializer(Unit.serializer()),
                value = CommonDataModel(
                    status = 1,
                    error = errorMessage
                )
            )
        )
    }

    @JvmStatic
    fun printMissingArgumentMessageForApi(
        argumentSummary: String
    ) {
        printErrorMessageForApi(errorMessage = "Missing $argumentSummary in command line arguments & environment file")
    }
}
