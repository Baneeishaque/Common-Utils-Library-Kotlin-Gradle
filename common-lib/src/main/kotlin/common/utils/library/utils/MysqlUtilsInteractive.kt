package common.utils.library.utils

import common.utils.library.models.IsOkModel
import common.utils.library.utils.InteractiveUtils.printErrorMessage
import common.utils.library.utils.MysqlUtils.dateTimeTextConversion

object MysqlUtilsInteractive {

    @JvmStatic
    fun dateTimeTextConversionWithMessage(

        inputDateTimeText: String,
        dateTimeTextConversionFunction: () -> IsOkModel<String>

    ): IsOkModel<String> {

        return dateTimeTextConversion(

            dateTimeTextConversionFunction = dateTimeTextConversionFunction,
            dateTimeTextConversionFunctionFailureActions = {

                printDateErrorMessage(inputDateTimeText)
            },
        )
    }

    @JvmStatic
    fun printDateErrorMessage(data: String) {

        printErrorMessage(data = data, dateSpecification = "Date")
    }
}