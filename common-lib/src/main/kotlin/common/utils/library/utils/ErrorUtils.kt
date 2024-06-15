package common.utils.library.utils

import common.utils.library.constants.ConstantsCommon

object ErrorUtils {

    @JvmStatic
    fun constructErrorMessage(dataSpecification: String, message: String? = null): String = if (message != null) {

        "$dataSpecification Error : $message"

    } else {

        "$dataSpecification Error"
    }

    @JvmStatic
    fun generateDataConfirmationErrorMessage(dataSpecifier: String): String {

        return "${ConstantsCommon.DATA_CONFIRMATION_ERROR_TEXT} for $dataSpecifier"
    }

    @JvmStatic
    fun generateInvalidInputMessage(inputSpecifier: String): String {

        return "No Valid \"$inputSpecifier\" Provided by User"
    }
}
