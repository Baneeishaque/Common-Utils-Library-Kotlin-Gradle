package common.utils.library.utils

import common.utils.library.models.IsOkModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern
import java.time.format.DateTimeParseException

object DateTimeUtils {

    @JvmStatic
    val normalDateTimePatternAsText = "dd/MM/yyyy HH:mm:ss"

    @JvmStatic
    val normalDateTimePattern: DateTimeFormatter = ofPattern(normalDateTimePatternAsText)!!

    @JvmStatic
    val normalDatePattern: DateTimeFormatter = ofPattern("dd/MM/yyyy")!!

    @JvmStatic
    fun addDaysToNormalDateTimeInText(

        dateTimeInText: String,
        days: Int

    ): String {

        return addDaysToNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = days

        ).format(normalDateTimePattern)
    }

    @JvmStatic
    fun subtractDaysFromNormalDateTimeInText(

        dateTimeInText: String,
        days: Int

    ): String {

        return subtractDaysFromNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = days

        ).format(normalDateTimePattern)
    }

    @JvmStatic
    fun add5MinutesToNormalDateTimeInText(

        dateTimeInText: String

    ): String {

        return addMinutesToNormalDateTimeInText(

            dateTimeInText = dateTimeInText,
            minutes = 5
        )
    }

    @JvmStatic
    fun addMinutesToNormalDateTimeInText(

        dateTimeInText: String,
        minutes: Int

    ): String {

        return addMinutesToNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            minutes = minutes

        ).format(normalDateTimePattern)
    }

    @JvmStatic
    fun addDaysToNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        days: Int

    ): LocalDateTime {

        return LocalDateTime.parse(
            dateTimeInText,
            normalDateTimePattern
        ).plusDays(days.toLong())
    }

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToXAsDateTime(

        dateTimeInText: String,
        resetHour: Int = 9,
        resetMinute: Int = 0,
        resetSecond: Int = 0

    ): LocalDateTime {

        return resetTimeOnDateTimeInTextToXAsDateTime(

            dateTimeInText = dateTimeInText,
            dateTimePattern = normalDateTimePattern,
            resetHour = resetHour,
            resetMinute = resetMinute,
            resetSecond = resetSecond
        )
    }

    @JvmStatic
    fun resetTimeOnDateTimeInTextToXAsDateTime(

        dateTimeInText: String,
        dateTimePattern: DateTimeFormatter,
        resetHour: Int = 9,
        resetMinute: Int = 0,
        resetSecond: Int = 0

    ): LocalDateTime {

        return LocalDateTime.parse(dateTimeInText, dateTimePattern)
            .withHour(resetHour).withMinute(resetMinute).withSecond(resetSecond)
    }

    @JvmStatic
    fun resetTimeOnDateTimeInTextToX(

        dateTimeInText: String,
        dateTimePattern: DateTimeFormatter,
        resetHour: Int = 9,
        resetMinute: Int = 0,
        resetSecond: Int = 0

    ): String {

        return resetTimeOnDateTimeInTextToXAsDateTime(

            dateTimeInText,
            dateTimePattern,
            resetHour,
            resetMinute,
            resetSecond

        ).format(
            dateTimePattern
        )
    }

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToX(

        dateTimeInText: String,
        resetHour: Int = 9,
        resetMinute: Int = 0,
        resetSecond: Int = 0

    ): String {

        return resetTimeOnDateTimeInTextToX(dateTimeInText, normalDateTimePattern, resetHour, resetMinute, resetSecond)
    }

    @JvmStatic
    fun resetTimeOnDateTimeToX(

        dateTime: LocalDateTime,
        resetHour: Int = 9,
        resetMinute: Int = 0,
        resetSecond: Int = 0

    ): LocalDateTime {

        return dateTime.withHour(resetHour).withMinute(resetMinute).withSecond(resetSecond)
    }

    @JvmStatic
    fun resetTimeOnDateTimeToXAsText(

        dateTime: LocalDateTime,
        dateTimePattern: DateTimeFormatter,
        resetHour: Int = 9,
        resetMinute: Int = 0,
        resetSecond: Int = 0

    ): String {

        return resetTimeOnDateTimeToX(dateTime, resetHour, resetMinute, resetSecond).format(dateTimePattern)
    }

    @JvmStatic
    fun resetTimeOnNormalDateTimeToXAsText(

        dateTime: LocalDateTime,
        resetHour: Int = 9,
        resetMinute: Int = 0,
        resetSecond: Int = 0

    ): String {

        return resetTimeOnDateTimeToXAsText(dateTime, normalDateTimePattern, resetHour, resetMinute, resetSecond)
    }

    @JvmStatic
    fun subtractDaysFromNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        days: Int

    ): LocalDateTime {

        return LocalDateTime.parse(
            dateTimeInText,
            normalDateTimePattern
        ).minusDays(days.toLong())
    }

    @JvmStatic
    fun addMinutesToNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        minutes: Int

    ): LocalDateTime {

        return LocalDateTime.parse(
            dateTimeInText, normalDateTimePattern
        ).plusMinutes(minutes.toLong())
    }

    @JvmStatic
    fun subtractSecondsFromMySqlDateTimeInTextAsDateTime(

        dateTimeInText: String,
        seconds: Int

    ): LocalDateTime {

        return LocalDateTime.parse(
            dateTimeInText, MysqlUtils.mysqlDateTimePattern
        ).minusSeconds(seconds.toLong())
    }

    @JvmStatic
    fun subtractSecondsFromMySqlDateTimeInText(

        dateTimeInText: String,
        seconds: Int

    ): String {

        return subtractSecondsFromMySqlDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            seconds = seconds

        ).format(MysqlUtils.mysqlDateTimePattern)
    }

    @JvmStatic
    fun subtract1SecondFromMySqlDateTimeInText(

        dateTimeInText: String

    ): String {

        return subtractSecondsFromMySqlDateTimeInText(

            dateTimeInText = dateTimeInText,
            seconds = 1
        )
    }

    @JvmStatic
    fun add1DayToNormalDateTimeInText(dateTimeInText: String): String {

        return addDaysToNormalDateTimeInText(

            dateTimeInText = dateTimeInText,
            days = 1
        )
    }

    @JvmStatic
    fun subtract1DayFromNormalDateTimeInText(dateTimeInText: String): String {

        return subtractDaysFromNormalDateTimeInText(

            dateTimeInText = dateTimeInText,
            days = 1
        )
    }

    @JvmStatic
    fun add1DayToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return addDaysToNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = 1
        )
    }

    @JvmStatic
    fun subtract1DayFromNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return subtractDaysFromNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = 1
        )
    }

    @JvmStatic
    fun add2DaysToNormalDateTimeInText(dateTimeInText: String): String {

        return addDaysToNormalDateTimeInText(

            dateTimeInText = dateTimeInText,
            days = 2
        )
    }

    @JvmStatic
    fun subtract2DaysFromNormalDateTimeInText(dateTimeInText: String): String {

        return subtractDaysFromNormalDateTimeInText(

            dateTimeInText = dateTimeInText,
            days = 2
        )
    }

    @JvmStatic
    fun add2DaysToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return addDaysToNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = 2
        )
    }

    @JvmStatic
    fun subtract2DaysFromNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return subtractDaysFromNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = 2
        )
    }

    @JvmStatic
    fun add1DayWith9ClockTimeToNormalDateTimeInText(dateTimeInText: String): String {

        return add1DayWith9ClockTimeToNormalDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).format(
            normalDateTimePattern
        )
    }

    @JvmStatic
    fun subtract1DayWith9ClockTimeToNormalDateTimeInText(dateTimeInText: String): String {

        return subtract1DayWith9ClockTimeToNormalDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).format(
            normalDateTimePattern
        )
    }

    @JvmStatic
    fun add1DayWith9ClockTimeToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return resetTimeOnDateTimeToX(dateTime = add1DayToNormalDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText))
    }

    @JvmStatic
    fun subtract1DayWith9ClockTimeToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return resetTimeOnDateTimeToX(dateTime = subtract1DayFromNormalDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText))
    }

    @JvmStatic
    fun add2DaysWith9ClockTimeToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return resetTimeOnDateTimeToX(dateTime = add2DaysToNormalDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText))
    }

    @JvmStatic
    fun subtract2DaysWith9ClockTimeFromNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return resetTimeOnDateTimeToX(dateTime = subtract2DaysFromNormalDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText))
    }

    @JvmStatic
    fun add2DaysWith9ClockTimeToNormalDateTimeInText(dateTimeInText: String): String {

        return add2DaysWith9ClockTimeToNormalDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).format(
            normalDateTimePattern
        )
    }

    fun subtract2DaysWith9ClockTimeFromNormalDateTimeInText(dateTimeInText: String): String {

        return subtract2DaysWith9ClockTimeFromNormalDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).format(
            normalDateTimePattern
        )
    }

    @JvmStatic
    fun getCurrentNormalDateTimeInText(): String {

        return LocalDateTime.now().format(normalDateTimePattern)
    }

    fun normalDateTimeInTextToDateTime(

        normalDateTimeInText: String,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: (DateTimeParseException) -> Unit = fun(_) {}

    ): IsOkModel<LocalDateTime> {

        return dateTimeInTextToDateTime(

            dateTimeInText = normalDateTimeInText,
            dateTimeTextPattern = normalDateTimePattern,
            conversionSuccessActions = conversionSuccessActions,
            conversionFailureActions = conversionFailureActions
        )
    }

    fun normalDateTimeInTextToDateTimeInteractive(

        normalDateTimeInText: String,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: () -> Unit = fun() {}

    ): IsOkModel<LocalDateTime> {

        return dateTimeInTextToDateTimeInteractive(

            dateTimeInText = normalDateTimeInText,
            dateTimeTextPattern = normalDateTimePattern,
            conversionSuccessActions = conversionSuccessActions,
            conversionFailureActions = conversionFailureActions
        )
    }

    fun dateTimeInTextToDateTime(

        dateTimeInText: String,
        dateTimeTextPattern: DateTimeFormatter,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: (DateTimeParseException) -> Unit = fun(_: DateTimeParseException) {}

    ): IsOkModel<LocalDateTime> {

        return try {

            val result: LocalDateTime = LocalDateTime.parse(dateTimeInText, dateTimeTextPattern)
            conversionSuccessActions.invoke()
            IsOkModel(isOK = true, data = result)

        } catch (dateTimeParseException: DateTimeParseException) {

            conversionFailureActions.invoke(dateTimeParseException)
            IsOkModel(isOK = false)
        }
    }

    @JvmStatic
    fun dateTimeInTextToDateTimeInteractive(

        dateTimeInText: String,
        dateTimeTextPattern: DateTimeFormatter,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: () -> Unit = fun() {}

    ): IsOkModel<LocalDateTime> {

        return dateTimeInTextToDateTime(

            dateTimeInText,
            dateTimeTextPattern,
            conversionSuccessActions,
            fun(dateTimeParseException) {

                println("Date Error : ${dateTimeParseException.localizedMessage}")
                conversionFailureActions.invoke()
            },
        )
    }
}
