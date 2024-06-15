package common.utils.library.utils

import common.utils.library.models.FailureBasedOnIsOkModel
import common.utils.library.models.IsOkModel
import common.utils.library.models.SuccessBasedOnIsOkModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object MysqlUtils {

    @JvmStatic
    val mysqlDateTimePattern: DateTimeFormatter = DateTimeFormatter.ofPattern(/* pattern = */ "yyyy-MM-dd HH:mm:ss")!!

    @JvmStatic
    val mysqlDatePattern: DateTimeFormatter = DateTimeFormatter.ofPattern(/* pattern = */ "yyyy-MM-dd")!!

    @JvmStatic
    fun normalDateTimeTextToMySqlDateTimeText(

        normalDateTimeText: String,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: () -> Unit = fun() {}

    ): IsOkModel<String> = try {

        val result: String = LocalDateTime.parse(normalDateTimeText, DateTimeUtils.normalDateTimePattern)
            .format(mysqlDateTimePattern)
        conversionSuccessActions.invoke()
        SuccessBasedOnIsOkModel(ownData = result)

    } catch (e: DateTimeParseException) {

        conversionFailureActions.invoke()
        FailureBasedOnIsOkModel(ownError = e.localizedMessage)
    }

    @JvmStatic
    fun mySqlDateTimeTextToNormalDateTimeText(

        mySqlDateTimeText: String,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: () -> Unit = fun() {}

    ): IsOkModel<String> = try {

        val result: String = LocalDateTime.parse(mySqlDateTimeText, mysqlDateTimePattern)
            .format(DateTimeUtils.normalDateTimePattern)
        conversionSuccessActions.invoke()
        SuccessBasedOnIsOkModel(ownData = result)

    } catch (e: DateTimeParseException) {

        conversionFailureActions.invoke()
        FailureBasedOnIsOkModel(ownError = e.localizedMessage)
    }

    @JvmStatic
    fun mySqlDateTimeTextToDateTime(

        mySqlDateTimeText: String

    ): IsOkModel<LocalDateTime> = DateTimeUtils.dateTimeInTextToDateTime(

        dateTimeInText = mySqlDateTimeText,
        dateTimeTextPattern = mysqlDateTimePattern
    )

    @JvmStatic
    fun <T> dateTimeTextConversion(

        dateTimeTextConversionFunction: () -> IsOkModel<T>,
        dateTimeTextConversionFunctionFailureActions: () -> Unit = {}

    ): IsOkModel<T> {

        val dateTimeConversionResult: IsOkModel<T> = dateTimeTextConversionFunction.invoke()
        if (dateTimeConversionResult.isOK) {

            return SuccessBasedOnIsOkModel(

                ownData = dateTimeConversionResult.data!!
            )

        } else {

            dateTimeTextConversionFunctionFailureActions.invoke()
        }
        return FailureBasedOnIsOkModel(

            ownError = dateTimeConversionResult.error!!
        )
    }

    @JvmStatic
    fun normalDateTextToMySqlDateText(normalDateText: String): IsOkModel<String> = try {

        val result: String =
            LocalDate.parse(normalDateText, DateTimeUtils.normalDatePattern).format(mysqlDatePattern)
        SuccessBasedOnIsOkModel(ownData = result)

    } catch (e: DateTimeParseException) {

        FailureBasedOnIsOkModel(ownError = e.localizedMessage)
    }

    //TODO : Date to MySQL Date String
    //TODO : DateTime to MySQL DateTime String
}
