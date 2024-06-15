package common.utils.library.utils

import common.utils.library.models.IsOkModel
import common.utils.library.utils.DateTimeUtils.dateTimeInTextToDateTime
import common.utils.library.utils.DateTimeUtils.normalDateTimePattern
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object DateTimeInteractiveUtils {

    @JvmStatic
    fun printDateErrorMessage(message: String): Unit = println(

        message = DateTimeUtils.constructDateErrorMessage(message = message)
    )

    @JvmStatic
    fun dateTimeInTextToDateTimeInteractive(

        dateTimeInText: String,
        dateTimeTextPattern: DateTimeFormatter,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: () -> Unit = fun() {}

    ): IsOkModel<LocalDateTime> = dateTimeInTextToDateTime(

        dateTimeInText = dateTimeInText,
        dateTimeTextPattern = dateTimeTextPattern,
        conversionSuccessActions = conversionSuccessActions,
        conversionFailureActions = fun(dateTimeParseException: DateTimeParseException) {

            println("Date Error : ${dateTimeParseException.localizedMessage}")
            conversionFailureActions.invoke()
        },
    )

    @JvmStatic
    fun normalDateTimeInTextToDateTimeInteractive(

        normalDateTimeInText: String,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: () -> Unit = fun() {}

    ): IsOkModel<LocalDateTime> = dateTimeInTextToDateTimeInteractive(

        dateTimeInText = normalDateTimeInText,
        dateTimeTextPattern = normalDateTimePattern,
        conversionSuccessActions = conversionSuccessActions,
        conversionFailureActions = conversionFailureActions
    )
}
