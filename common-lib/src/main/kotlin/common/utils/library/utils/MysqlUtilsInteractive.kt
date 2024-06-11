package common.utils.library.utils

import common.utils.library.models.IsOkModel
import java.time.LocalDateTime

object MysqlUtilsInteractive {

    @JvmStatic
    fun <T> dateTimeTextConversionWithMessage(

        inputDateTimeText: String,
        dateTimeTextConversionFunction: () -> IsOkModel<T>,
        dateTimeTextConversionFunctionFailureActions: () -> Unit = {}

    ): IsOkModel<T> {

        return MysqlUtils.dateTimeTextConversion<T>(

            dateTimeTextConversionFunction = dateTimeTextConversionFunction,
            dateTimeTextConversionFunctionFailureActions = {

                DateTimeInteractiveUtils.printDateErrorMessage(message = inputDateTimeText)
                dateTimeTextConversionFunctionFailureActions.invoke()
            },
        )
    }

    @JvmStatic
    fun normalDateTimeTextToMySqlDateTimeTextWithMessage(inputDateTimeText: String): IsOkModel<String> {

        return this.dateTimeTextConversionWithMessage<String>(

            inputDateTimeText = inputDateTimeText,
            dateTimeTextConversionFunction = fun(): IsOkModel<String> {

                return MysqlUtils.normalDateTimeTextToMySqlDateTimeText(

                    normalDateTimeText = inputDateTimeText,
                )
            }
        )
    }

    @JvmStatic
    fun mySqlDateTimeTextToNormalDateTimeTextWithMessage(mySqlDateTimeText: String): IsOkModel<String> {

        return this.dateTimeTextConversionWithMessage<String>(

            inputDateTimeText = mySqlDateTimeText,
            dateTimeTextConversionFunction = fun(): IsOkModel<String> {

                return MysqlUtils.mySqlDateTimeTextToNormalDateTimeText(mySqlDateTimeText = mySqlDateTimeText)
            },
        )
    }

    @JvmStatic
    fun mySqlDateTimeTextToDateTimeWithMessage(mySqlDateTimeText: String): IsOkModel<LocalDateTime> {

        return this.dateTimeTextConversionWithMessage<LocalDateTime>(

            inputDateTimeText = mySqlDateTimeText,
            dateTimeTextConversionFunction = fun(): IsOkModel<LocalDateTime> {

                return MysqlUtils.mySqlDateTimeTextToDateTime(mySqlDateTimeText = mySqlDateTimeText)
            },
        )
    }
}
