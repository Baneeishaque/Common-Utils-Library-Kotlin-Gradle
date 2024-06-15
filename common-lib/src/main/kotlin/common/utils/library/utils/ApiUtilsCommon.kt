package common.utils.library.utils

object ApiUtilsCommon {

    @JvmStatic
    fun getServerApiMethodAbsoluteUrl(

        serverApiMethodName: String,
        serverFileExtension: String

    ): String =
        "$serverApiMethodName.$serverFileExtension"

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
    fun <T> apiResponseHandler(

        apiResponse: Result<T>,
        apiFailureActions: () -> Unit = fun() {},
        apiSuccessActions: (T) -> Unit

    ): Unit = if (apiResponse.isFailure) {

        apiFailureActions.invoke()

    } else {

        apiSuccessActions.invoke(apiResponse.getOrNull()!!)
    }
}
