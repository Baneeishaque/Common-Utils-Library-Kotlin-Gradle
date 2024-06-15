package common.utils.library.utils

import common.utils.library.constants.ConstantsCommon
import common.utils.library.enums.TimePartManipulationEnum
import common.utils.library.enums.TimePartSpecificationEnum
import common.utils.library.models.FailureBasedOnIsOkModel
import common.utils.library.models.FailureWithoutExplanationBasedOnIsOkModel
import common.utils.library.models.IsOkModel
import common.utils.library.models.SuccessBasedOnIsOkModel
import java.time.LocalDate
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
    val normalDatePattern: DateTimeFormatter = ofPattern(/* pattern = */ "dd/MM/yyyy")!!

    @JvmStatic
    fun addDaysToNormalDateTimeInText(

        dateTimeInText: String,
        days: Int

    ): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = days,
        timePartSpecification = TimePartSpecificationEnum.DAY,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun subtractDaysFromNormalDateTimeInText(

        dateTimeInText: String,
        days: Int

    ): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = days,
        timePartSpecification = TimePartSpecificationEnum.DAY,
        timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
    )

    @JvmStatic
    fun add5MinutesToNormalDateTimeInText(

        dateTimeInText: String

    ): String = addMinutesToNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        minutes = 5
    )

    @JvmStatic
    fun addHoursToNormalDateTimeInText(

        dateTimeInText: String,
        hours: Int

    ): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = hours,
        timePartSpecification = TimePartSpecificationEnum.HOUR,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun addMinutesToNormalDateTimeInText(

        dateTimeInText: String,
        minutes: Int

    ): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = minutes,
        timePartSpecification = TimePartSpecificationEnum.MINUTE,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun addSecondsToNormalDateTimeInText(

        dateTimeInText: String,
        seconds: Int

    ): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = seconds,
        timePartSpecification = TimePartSpecificationEnum.SECOND,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun addDaysToNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        days: Int

    ): LocalDateTime = manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        timePartQuantity = days,
        timePartSpecification = TimePartSpecificationEnum.DAY,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToXAsDateTime(

        dateTimeInText: String,
        resetHour: Int = ConstantsCommon.defaultTimeResetHour,
        resetMinute: Int = ConstantsCommon.defaultTimeResetMinute,
        resetSecond: Int = ConstantsCommon.defaultTimeResetSecond

    ): LocalDateTime = resetTimeOnDateTimeInTextToXAsDateTime(

        dateTimeInText = dateTimeInText,
        dateTimePattern = normalDateTimePattern,
        resetHour = resetHour,
        resetMinute = resetMinute,
        resetSecond = resetSecond
    )

    @JvmStatic
    fun resetTimeOnDateTimeInTextToXAsDateTime(

        dateTimeInText: String,
        dateTimePattern: DateTimeFormatter,
        resetHour: Int = ConstantsCommon.defaultTimeResetHour,
        resetMinute: Int = ConstantsCommon.defaultTimeResetMinute,
        resetSecond: Int = ConstantsCommon.defaultTimeResetSecond

    ): LocalDateTime = LocalDateTime.parse(

        /* text = */ dateTimeInText,
        /* formatter = */ dateTimePattern

    ).withHour(

        /* hour = */ resetHour

    ).withMinute(

        /* minute = */ resetMinute

    ).withSecond(

        /* second = */ resetSecond
    )

    @JvmStatic
    fun resetTimeOnDateTimeInTextToX(

        dateTimeInText: String,
        dateTimePattern: DateTimeFormatter,
        resetHour: Int = ConstantsCommon.defaultTimeResetHour,
        resetMinute: Int = ConstantsCommon.defaultTimeResetMinute,
        resetSecond: Int = ConstantsCommon.defaultTimeResetSecond

    ): String = resetTimeOnDateTimeInTextToXAsDateTime(

        dateTimeInText = dateTimeInText,
        dateTimePattern = dateTimePattern,
        resetHour = resetHour,
        resetMinute = resetMinute,
        resetSecond = resetSecond

    ).format(

        /* formatter = */ dateTimePattern
    )

    @JvmStatic
    fun resetTimeOnNormalDateTimeInTextToX(

        dateTimeInText: String,
        resetHour: Int = ConstantsCommon.defaultTimeResetHour,
        resetMinute: Int = ConstantsCommon.defaultTimeResetMinute,
        resetSecond: Int = ConstantsCommon.defaultTimeResetSecond

    ): String = resetTimeOnDateTimeInTextToX(

        dateTimeInText = dateTimeInText,
        dateTimePattern = normalDateTimePattern,
        resetHour = resetHour,
        resetMinute = resetMinute,
        resetSecond = resetSecond
    )

    @JvmStatic
    fun resetTimeOnDateTimeToX(

        dateTime: LocalDateTime,
        resetHour: Int = ConstantsCommon.defaultTimeResetHour,
        resetMinute: Int = ConstantsCommon.defaultTimeResetMinute,
        resetSecond: Int = ConstantsCommon.defaultTimeResetSecond

    ): LocalDateTime = dateTime.withHour(

        /* hour = */ resetHour

    ).withMinute(

        /* minute = */ resetMinute

    ).withSecond(

        /* second = */ resetSecond
    )

    @JvmStatic
    fun resetTimeOnDateTimeToXAsText(

        dateTime: LocalDateTime,
        dateTimePattern: DateTimeFormatter,
        resetHour: Int = ConstantsCommon.defaultTimeResetHour,
        resetMinute: Int = ConstantsCommon.defaultTimeResetMinute,
        resetSecond: Int = ConstantsCommon.defaultTimeResetSecond

    ): String = resetTimeOnDateTimeToX(

        dateTime = dateTime,
        resetHour = resetHour,
        resetMinute = resetMinute,
        resetSecond = resetSecond

    ).format(

        /* formatter = */ dateTimePattern
    )

    @JvmStatic
    fun resetTimeOnNormalDateTimeToXAsText(

        dateTime: LocalDateTime,
        resetHour: Int = ConstantsCommon.defaultTimeResetHour,
        resetMinute: Int = ConstantsCommon.defaultTimeResetMinute,
        resetSecond: Int = ConstantsCommon.defaultTimeResetSecond

    ): String = resetTimeOnDateTimeToXAsText(

        dateTime = dateTime,
        dateTimePattern = normalDateTimePattern,
        resetHour = resetHour,
        resetMinute = resetMinute,
        resetSecond = resetSecond
    )

    @JvmStatic
    fun subtractDaysFromNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        days: Int

    ): LocalDateTime = manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        timePartQuantity = days,
        timePartSpecification = TimePartSpecificationEnum.DAY,
        timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
    )

    @JvmStatic
    fun addHoursToNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        hours: Int

    ): LocalDateTime = manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        timePartQuantity = hours,
        timePartSpecification = TimePartSpecificationEnum.HOUR,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun addMinutesToNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        minutes: Int

    ): LocalDateTime = manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        timePartQuantity = minutes,
        timePartSpecification = TimePartSpecificationEnum.MINUTE,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun addSecondsToNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        seconds: Int

    ): LocalDateTime = manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        timePartQuantity = seconds,
        timePartSpecification = TimePartSpecificationEnum.SECOND,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun subtractHoursFromNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        hours: Int

    ): LocalDateTime = manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        timePartQuantity = hours,
        timePartSpecification = TimePartSpecificationEnum.HOUR,
        timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
    )

    @JvmStatic
    fun subtractMinutesFromNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        minutes: Int

    ): LocalDateTime = manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        timePartQuantity = minutes,
        timePartSpecification = TimePartSpecificationEnum.MINUTE,
        timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
    )

    @JvmStatic
    fun subtractSecondsFromNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        seconds: Int

    ): LocalDateTime = manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        timePartQuantity = seconds,
        timePartSpecification = TimePartSpecificationEnum.SECOND,
        timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
    )

    @JvmStatic
    fun subtractHoursFromNormalDateTimeInText(

        dateTimeInText: String,
        hours: Int

    ): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = hours,
        timePartSpecification = TimePartSpecificationEnum.HOUR,
        timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
    )

    @JvmStatic
    fun subtractMinutesFromNormalDateTimeInText(

        dateTimeInText: String,
        minutes: Int

    ): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = minutes,
        timePartSpecification = TimePartSpecificationEnum.MINUTE,
        timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
    )

    @JvmStatic
    fun subtractSecondsFromNormalDateTimeInText(

        dateTimeInText: String,
        seconds: Int

    ): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = seconds,
        timePartSpecification = TimePartSpecificationEnum.SECOND,
        timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
    )

    @JvmStatic
    private fun manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText: String,
        timePartQuantity: Int,
        timePartSpecification: TimePartSpecificationEnum,
        timePartManipulationActivity: TimePartManipulationEnum

    ): LocalDateTime {

        val result: LocalDateTime = LocalDateTime.parse(

            /* text = */ dateTimeInText,
            /* formatter = */ normalDateTimePattern
        )
        when (timePartManipulationActivity) {

            TimePartManipulationEnum.ADDITION -> {

                return when (timePartSpecification) {

                    TimePartSpecificationEnum.HOUR -> result.plusHours(timePartQuantity.toLong())
                    TimePartSpecificationEnum.MINUTE -> result.plusMinutes(timePartQuantity.toLong())
                    TimePartSpecificationEnum.SECOND -> result.plusSeconds(timePartQuantity.toLong())
                    TimePartSpecificationEnum.DAY -> result.plusDays(timePartQuantity.toLong())
                }
            }

            TimePartManipulationEnum.SUBTRACTION -> {

                return when (timePartSpecification) {

                    TimePartSpecificationEnum.HOUR -> result.minusHours(timePartQuantity.toLong())
                    TimePartSpecificationEnum.MINUTE -> result.minusMinutes(timePartQuantity.toLong())
                    TimePartSpecificationEnum.SECOND -> result.minusSeconds(timePartQuantity.toLong())
                    TimePartSpecificationEnum.DAY -> result.minusDays(timePartQuantity.toLong())
                }
            }
        }
    }

    @JvmStatic
    private fun manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText: String,
        timePartQuantity: Int,
        timePartSpecification: TimePartSpecificationEnum,
        timePartManipulationActivity: TimePartManipulationEnum

    ): String = manipulateTimePartOnNormalDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        timePartQuantity = timePartQuantity,
        timePartSpecification = timePartSpecification,
        timePartManipulationActivity = timePartManipulationActivity

    ).format(

        /* formatter = */ normalDateTimePattern
    )

    @JvmStatic
    fun subtractSecondsFromMySqlDateTimeInTextAsDateTime(

        dateTimeInText: String,
        seconds: Int

    ): LocalDateTime = LocalDateTime.parse(

        /* text = */ dateTimeInText,
        /* formatter = */ MysqlUtils.mysqlDateTimePattern

    ).minusSeconds(

        /* seconds = */ seconds.toLong()
    )

    @JvmStatic
    fun subtractSecondsFromMySqlDateTimeInText(

        dateTimeInText: String,
        seconds: Int

    ): String = subtractSecondsFromMySqlDateTimeInTextAsDateTime(

        dateTimeInText = dateTimeInText,
        seconds = seconds

    ).format(

        /* formatter = */ MysqlUtils.mysqlDateTimePattern
    )

    @JvmStatic
    fun subtract1SecondFromMySqlDateTimeInText(

        dateTimeInText: String

    ): String = subtractSecondsFromMySqlDateTimeInText(

        dateTimeInText = dateTimeInText,
        seconds = 1
    )

    @JvmStatic
    fun add1DayToNormalDateTimeInText(dateTimeInText: String): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = 1,
        timePartSpecification = TimePartSpecificationEnum.DAY,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun subtract1DayFromNormalDateTimeInText(dateTimeInText: String): String =
        manipulateTimePartOnNormalDateTimeInText(

            dateTimeInText = dateTimeInText,
            timePartQuantity = 1,
            timePartSpecification = TimePartSpecificationEnum.DAY,
            timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
        )

    @JvmStatic
    fun add1DayToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime =
        manipulateTimePartOnNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            timePartQuantity = 1,
            timePartSpecification = TimePartSpecificationEnum.DAY,
            timePartManipulationActivity = TimePartManipulationEnum.ADDITION
        )

    @JvmStatic
    fun subtract1DayFromNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime =
        manipulateTimePartOnNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            timePartQuantity = 1,
            timePartSpecification = TimePartSpecificationEnum.DAY,
            timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
        )

    @JvmStatic
    fun add2DaysToNormalDateTimeInText(dateTimeInText: String): String = manipulateTimePartOnNormalDateTimeInText(

        dateTimeInText = dateTimeInText,
        timePartQuantity = 2,
        timePartSpecification = TimePartSpecificationEnum.DAY,
        timePartManipulationActivity = TimePartManipulationEnum.ADDITION
    )

    @JvmStatic
    fun subtract2DaysFromNormalDateTimeInText(dateTimeInText: String): String =
        manipulateTimePartOnNormalDateTimeInText(

            dateTimeInText = dateTimeInText,
            timePartQuantity = 2,
            timePartSpecification = TimePartSpecificationEnum.DAY,
            timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
        )

    @JvmStatic
    fun add2DaysToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime =
        manipulateTimePartOnNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            timePartQuantity = 2,
            timePartSpecification = TimePartSpecificationEnum.DAY,
            timePartManipulationActivity = TimePartManipulationEnum.ADDITION
        )

    @JvmStatic
    fun subtract2DaysFromNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime =
        manipulateTimePartOnNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText,
            timePartQuantity = 2,
            timePartSpecification = TimePartSpecificationEnum.DAY,
            timePartManipulationActivity = TimePartManipulationEnum.SUBTRACTION
        )

    @JvmStatic
    fun add1DayWith9ClockTimeToNormalDateTimeInText(dateTimeInText: String): String =
        add1DayWith9ClockTimeToNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText

        ).format(

            /* formatter = */ normalDateTimePattern
        )

    @JvmStatic
    fun subtract1DayWith9ClockTimeToNormalDateTimeInText(dateTimeInText: String): String =
        subtract1DayWith9ClockTimeToNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText

        ).format(

            /* formatter = */ normalDateTimePattern
        )

    @JvmStatic
    fun add1DayWith9ClockTimeToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime =
        resetTimeOnDateTimeToX(

            dateTime = add1DayToNormalDateTimeInTextAsDateTime(

                dateTimeInText = dateTimeInText
            )
        )

    @JvmStatic
    fun subtract1DayWith9ClockTimeToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime =
        resetTimeOnDateTimeToX(

            dateTime = subtract1DayFromNormalDateTimeInTextAsDateTime(

                dateTimeInText = dateTimeInText
            )
        )

    @JvmStatic
    fun add2DaysWith9ClockTimeToNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime =
        resetTimeOnDateTimeToX(

            dateTime = add2DaysToNormalDateTimeInTextAsDateTime(

                dateTimeInText = dateTimeInText
            )
        )

    @JvmStatic
    fun subtract2DaysWith9ClockTimeFromNormalDateTimeInTextAsDateTime(dateTimeInText: String): LocalDateTime =
        resetTimeOnDateTimeToX(

            dateTime = subtract2DaysFromNormalDateTimeInTextAsDateTime(

                dateTimeInText = dateTimeInText
            )
        )

    @JvmStatic
    fun add2DaysWith9ClockTimeToNormalDateTimeInText(dateTimeInText: String): String =
        add2DaysWith9ClockTimeToNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText

        ).format(

            /* formatter = */ normalDateTimePattern
        )

    @JvmStatic
    fun subtract2DaysWith9ClockTimeFromNormalDateTimeInText(dateTimeInText: String): String =
        subtract2DaysWith9ClockTimeFromNormalDateTimeInTextAsDateTime(

            dateTimeInText = dateTimeInText

        ).format(

            /* formatter = */ normalDateTimePattern
        )

    @JvmStatic
    fun getCurrentNormalDateTimeInText(): String = LocalDateTime.now().format(

        /* formatter = */ normalDateTimePattern
    )

    @JvmStatic
    fun normalDateTimeInTextToDateTime(

        normalDateTimeInText: String,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: (DateTimeParseException) -> Unit = fun(_: DateTimeParseException) {}

    ): IsOkModel<LocalDateTime> = dateTimeInTextToDateTime(

        dateTimeInText = normalDateTimeInText,
        dateTimeTextPattern = normalDateTimePattern,
        conversionSuccessActions = conversionSuccessActions,
        conversionFailureActions = conversionFailureActions
    )

    @JvmStatic
    fun dateTimeInTextToDateTime(

        dateTimeInText: String,
        dateTimeTextPattern: DateTimeFormatter,
        conversionSuccessActions: () -> Unit = fun() {},
        conversionFailureActions: (DateTimeParseException) -> Unit = fun(_: DateTimeParseException) {}

    ): IsOkModel<LocalDateTime> = try {

        val result: LocalDateTime = LocalDateTime.parse(

            /* text = */ dateTimeInText,
            /* formatter = */ dateTimeTextPattern
        )
        conversionSuccessActions.invoke()
        SuccessBasedOnIsOkModel(ownData = result)

    } catch (dateTimeParseException: DateTimeParseException) {

        conversionFailureActions.invoke(dateTimeParseException)
        FailureBasedOnIsOkModel(ownError = dateTimeParseException.localizedMessage)
    }

    @JvmStatic
    fun parseDateWithPatterns(

        dateString: String,
        patterns: List<String>

    ): IsOkModel<LocalDate> {

        if (patterns.isEmpty()) {

            return FailureWithoutExplanationBasedOnIsOkModel()
        }

        return try {

            SuccessBasedOnIsOkModel(

                ownData = LocalDate.parse(

                    /* text = */ dateString,
                    /* formatter = */ ofPattern(patterns.first())
                )
            )

        } catch (e: DateTimeParseException) {

            parseDateWithPatterns(

                dateString = dateString,
                patterns = patterns.drop(n = 1)
            )
        }
    }

    @JvmStatic
    fun constructDateErrorMessage(message: String): String =
        ErrorUtils.constructErrorMessage(dataSpecification = "Date", message = message)
}
