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

    //TODO : extract into function
    private const val resetHour = 9
    private const val resetMinute = 0
    private const val resetSecond = 0

    @JvmStatic
    fun addDaysToDateTimeInText(

        dateTimeInText: String,
        days: Int

    ): String {

        return addDaysToDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = days

        ).format(normalDateTimePattern)
    }

    @JvmStatic
    fun subtractDaysToDateTimeInText(

        dateTimeInText: String,
        days: Int

    ): String {

        return subtractDaysToDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = days

        ).format(normalDateTimePattern)
    }

    @JvmStatic
    fun add5MinutesToDateTimeInText(

        dateTimeInText: String

    ): String {

        return addMinutesToDateTimeInText(

            dateTimeInText = dateTimeInText,
            minutes = 5
        )
    }

    @JvmStatic
    fun addMinutesToDateTimeInText(

        dateTimeInText: String,
        minutes: Int

    ): String {

        return addMinutesToDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            minutes = minutes

        ).format(normalDateTimePattern)
    }

    @JvmStatic
    fun addDaysToDateTimeInTextAsDateTime(

        dateTimeInText: String,
        days: Int

    ): LocalDateTime {

        return LocalDateTime.parse(
            dateTimeInText,
            normalDateTimePattern
        ).plusDays(days.toLong())
    }

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToXAsDateTime(dateTimeInText: String): LocalDateTime {

        return resetTimeOnDateTimeInTextToXAsDateTime(

            dateTimeInText = dateTimeInText,
            dateTimePattern = normalDateTimePattern,
            resetHour = resetHour,
            resetMinute = resetMinute,
            resetSecond = resetSecond
        )
    }

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToXAsDateTime(dateTimeInText: String, resetHour: Int): LocalDateTime {

        return resetTimeOnDateTimeInTextToXAsDateTime(

            dateTimeInText = dateTimeInText,
            dateTimePattern = normalDateTimePattern,
            resetHour = resetHour,
            resetMinute = resetMinute,
            resetSecond = resetSecond
        )
    }

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToXAsDateTime(

        dateTimeInText: String,
        resetHour: Int,
        resetMinute: Int

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
        resetHour: Int,
        resetMinute: Int,
        resetSecond: Int

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
        resetHour: Int,
        resetMinute: Int,
        resetSecond: Int

    ): LocalDateTime {

        return LocalDateTime.parse(dateTimeInText, dateTimePattern)
            .withHour(resetHour).withMinute(resetMinute).withSecond(resetSecond)
    }

    @JvmStatic
    fun resetTimeOnDateTimeInTextToX(

        dateTimeInText: String,
        dateTimePattern: DateTimeFormatter,
        resetHour: Int,
        resetMinute: Int,
        resetSecond: Int

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
        resetHour: Int,
        resetMinute: Int,
        resetSecond: Int

    ): String {

        return resetTimeOnDateTimeInTextToX(dateTimeInText, normalDateTimePattern, resetHour, resetMinute, resetSecond)
    }

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToX(

        dateTimeInText: String,
        resetHour: Int,
        resetMinute: Int

    ): String {

        return resetTimeOnNormalDateTimeInTextToX(dateTimeInText, resetHour, resetMinute, resetSecond)
    }

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToX(

        dateTimeInText: String,
        resetHour: Int

    ): String {

        return resetTimeOnNormalDateTimeInTextToX(dateTimeInText, resetHour, resetMinute, resetSecond)
    }

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToX(

        dateTimeInText: String

    ): String {

        return resetTimeOnNormalDateTimeInTextToX(dateTimeInText, resetHour, resetMinute, resetSecond)
    }

    @JvmStatic
    fun subtractDaysToDateTimeInTextAsDateTime(

        dateTimeInText: String,
        days: Int

    ): LocalDateTime {

        return LocalDateTime.parse(
            dateTimeInText,
            normalDateTimePattern
        ).minusDays(days.toLong())
    }

    @JvmStatic
    fun addMinutesToDateTimeInTextAsDateTime(

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
    fun add1DayToDateTimeInText(dateTimeInText: String): String {

        return addDaysToDateTimeInText(

            dateTimeInText = dateTimeInText,
            days = 1
        )
    }

    @JvmStatic
    fun subtract1DayToDateTimeInText(dateTimeInText: String): String {

        return subtractDaysToDateTimeInText(

            dateTimeInText = dateTimeInText,
            days = 1
        )
    }

    @JvmStatic
    fun add1DayToDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return addDaysToDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = 1
        )
    }

    @JvmStatic
    fun subtract1DayToDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return subtractDaysToDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = 1
        )
    }

    @JvmStatic
    fun add2DaysToDateTimeInText(dateTimeInText: String): String {

        return addDaysToDateTimeInText(

            dateTimeInText = dateTimeInText,
            days = 2
        )
    }

    @JvmStatic
    fun subtract2DaysToDateTimeInText(dateTimeInText: String): String {

        return subtractDaysToDateTimeInText(

            dateTimeInText = dateTimeInText,
            days = 2
        )
    }

    @JvmStatic
    fun add2DaysToDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return addDaysToDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = 2
        )
    }

    @JvmStatic
    fun subtract2DaysToDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return subtractDaysToDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            days = 2
        )
    }

    @JvmStatic
    fun add1DayWith9ClockTimeToDateTimeInText(dateTimeInText: String): String {

        return add1DayWith9ClockTimeToDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).format(
            normalDateTimePattern
        )
    }

    @JvmStatic
    fun subtract1DayWith9ClockTimeToDateTimeInText(dateTimeInText: String): String {

        return subtract1DayWith9ClockTimeToDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).format(
            normalDateTimePattern
        )
    }

    @JvmStatic
    fun add1DayWith9ClockTimeToDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return add1DayToDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).withHour(resetHour)
            .withMinute(resetMinute).withSecond(resetSecond)
    }

    @JvmStatic
    fun subtract1DayWith9ClockTimeToDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return subtract1DayToDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).withHour(resetHour)
            .withMinute(resetMinute).withSecond(resetSecond)
    }

    @JvmStatic
    fun add2DaysWith9ClockTimeToDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return add2DaysToDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).withHour(resetHour)
            .withMinute(resetMinute).withSecond(resetSecond)
    }

    @JvmStatic
    fun subtract2DaysWith9ClockTimeToDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime {

        return subtract2DaysToDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).withHour(resetHour)
            .withMinute(resetMinute).withSecond(resetSecond)
    }

    @JvmStatic
    fun add2DaysWith9ClockTimeToDateTimeInText(dateTimeInText: String): String {

        return add2DaysWith9ClockTimeToDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).format(
            normalDateTimePattern
        )
    }

    fun subtract2DaysWith9ClockTimeToDateTimeInText(dateTimeInText: String): String {

        return subtract2DaysWith9ClockTimeToDateTimeInTextAsDateTime(dateTimeInText = dateTimeInText).format(
            normalDateTimePattern
        )
    }

    @JvmStatic
    fun getCurrentDateTimeInText(): String {

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
