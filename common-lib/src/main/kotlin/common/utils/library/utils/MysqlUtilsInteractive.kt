package common.utils.library.utils

import common.utils.library.models.IsOkModel
import java.time.LocalDateTime

object MysqlUtilsInteractive {

    @JvmStatic
    fun <T> dateTimeTextConversionWithMessage(

        inputDateTimeText: String,
        dateTimeTextConversionFunction: () -> IsOkModel<T>,
        dateTimeTextConversionFunctionFailureActions: () -> Unit = {}

    ): IsOkModel<T> = MysqlUtils.dateTimeTextConversion(

        dateTimeTextConversionFunction = dateTimeTextConversionFunction,
        dateTimeTextConversionFunctionFailureActions = {

            DateTimeInteractiveUtils.printDateErrorMessage(message = inputDateTimeText)
            dateTimeTextConversionFunctionFailureActions.invoke()
        },
    )

    @JvmStatic
    fun normalDateTimeTextToMySqlDateTimeTextWithMessage(inputDateTimeText: String): IsOkModel<String> =
        this.dateTimeTextConversionWithMessage(

            inputDateTimeText = inputDateTimeText,
            dateTimeTextConversionFunction = fun(): IsOkModel<String> {

                return MysqlUtils.normalDateTimeTextToMySqlDateTimeText(

                    normalDateTimeText = inputDateTimeText,
                )
            }
        )

    @JvmStatic
    fun mySqlDateTimeTextToNormalDateTimeTextWithMessage(mySqlDateTimeText: String): IsOkModel<String> =
        this.dateTimeTextConversionWithMessage(

            inputDateTimeText = mySqlDateTimeText,
            dateTimeTextConversionFunction = fun(): IsOkModel<String> {

                return MysqlUtils.mySqlDateTimeTextToNormalDateTimeText(mySqlDateTimeText = mySqlDateTimeText)
            },
        )

    @JvmStatic
    fun mySqlDateTimeTextToDateTimeWithMessage(mySqlDateTimeText: String): IsOkModel<LocalDateTime> =
        this.dateTimeTextConversionWithMessage(

            inputDateTimeText = mySqlDateTimeText,
            dateTimeTextConversionFunction = fun(): IsOkModel<LocalDateTime> {

                return MysqlUtils.mySqlDateTimeTextToDateTime(mySqlDateTimeText = mySqlDateTimeText)
            },
        )
}
