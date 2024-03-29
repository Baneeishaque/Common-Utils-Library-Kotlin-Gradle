package common.utils.library.utils

import common.utils.library.models.IsOkModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object MysqlUtils {

    @JvmStatic
    val mysqlDateTimePattern: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")!!

    @JvmStatic
    val mysqlDatePattern: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")!!

    @JvmStatic
    fun normalDateTimeTextToMySqlDateTimeText(

        normalDateTimeText: String,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: () -> Unit = fun() {}

    ): IsOkModel<String> {

        return try {

            val result: String = LocalDateTime.parse(normalDateTimeText, DateTimeUtils.normalDateTimePattern)
                .format(mysqlDateTimePattern)
            conversionSuccessActions.invoke()
            IsOkModel(isOK = true, data = result)

        } catch (e: DateTimeParseException) {

            conversionFailureActions.invoke()
            IsOkModel(isOK = false, data = e.localizedMessage)
        }
    }

    @JvmStatic
    fun mySqlDateTimeTextToNormalDateTimeText(

        mySqlDateTimeText: String,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: () -> Unit = fun() {}

    ): IsOkModel<String> {

        return try {

            val result: String = LocalDateTime.parse(mySqlDateTimeText, mysqlDateTimePattern)
                .format(DateTimeUtils.normalDateTimePattern)
            conversionSuccessActions.invoke()
            IsOkModel(isOK = true, data = result)

        } catch (e: DateTimeParseException) {

            conversionFailureActions.invoke()
            IsOkModel(isOK = false, data = e.localizedMessage)
        }
    }

    @JvmStatic
    fun mySqlDateTimeTextToDateTime(

        mySqlDateTimeText: String,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: () -> Unit = fun() {}

    ): IsOkModel<LocalDateTime> {

        return DateTimeUtils.dateTimeInTextToDateTime(

            dateTimeInText = mySqlDateTimeText,
            dateTimeTextPattern = mysqlDateTimePattern
        )
    }

    @JvmStatic
    fun dateTimeTextConversion(

        dateTimeTextConversionFunction: () -> IsOkModel<String>,
        dateTimeTextConversionFunctionFailureActions: () -> Unit = {}

    ): IsOkModel<String> {

        val dateTimeConversionResult: IsOkModel<String> = dateTimeTextConversionFunction.invoke()
        if (dateTimeConversionResult.isOK) {

            return IsOkModel(isOK = true, data = dateTimeConversionResult.data!!)

        } else {

            dateTimeTextConversionFunctionFailureActions.invoke()
        }
        return IsOkModel(isOK = false)
    }

    @JvmStatic
    fun normalDateTextToMySqlDateText(normalDateText: String): IsOkModel<String> {

        return try {

            val result: String =
                LocalDate.parse(normalDateText, DateTimeUtils.normalDatePattern).format(mysqlDatePattern)
            IsOkModel(isOK = true, data = result)

        } catch (e: DateTimeParseException) {

            IsOkModel(isOK = false, data = e.localizedMessage)
        }
    }

    //TODO : Date to MySQL Date String
    //TODO : DateTime to MySQL DateTime String
}
