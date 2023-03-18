package common.utils.library.utils

import common.utils.library.models.IsOkModel
import common.utils.library.utils.InteractiveUtils.printErrorMessage

object MysqlUtilsInteractive {

    @JvmStatic
    fun dateTimeTextConversionWithMessage(

        inputDateTimeText: String,
        dateTimeTextConversionFunction: () -> IsOkModel<String>,
        dateTimeTextConversionFunctionFailureActions: () -> Unit = {}

    ): IsOkModel<String> {

        return MysqlUtils.dateTimeTextConversion(

            dateTimeTextConversionFunction = dateTimeTextConversionFunction,
            dateTimeTextConversionFunctionFailureActions = {

                printDateErrorMessage(inputDateTimeText)
                dateTimeTextConversionFunctionFailureActions.invoke()
            },
        )
    }

    @JvmStatic
    fun printDateErrorMessage(data: String) {

        printErrorMessage(

            data = data,
            dateSpecification = "Date"
        )
    }
}