package common.utils.library.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

object InputUtils {

    @JvmStatic
    fun getValidFloat(inputText: String, constructInvalidMessage: (String) -> String): Float {

        return try {
            inputText.toFloat()

        } catch (exception: NumberFormatException) {

            print(constructInvalidMessage.invoke(inputText))
            getValidFloat(inputText = readln(), constructInvalidMessage = constructInvalidMessage)
        }
    }

    @JvmStatic
    fun getValidUnsignedInt(inputText: String, invalidMessage: String): UInt {

        return try {

            inputText.toUInt()

        } catch (exception: NumberFormatException) {

            print(message = invalidMessage)
            getValidUnsignedInt(inputText = readln(), invalidMessage = invalidMessage)
        }
    }

    @JvmStatic
    fun getGreaterUnsignedInt(inputUInt: UInt, thresholdValue: UInt, constructInvalidMessage: (UInt) -> String): UInt {

        if (inputUInt > thresholdValue) {

            return inputUInt

        } else {

            print(message = constructInvalidMessage.invoke(inputUInt))
            return getGreaterUnsignedInt(

                inputUInt = getValidUnsignedInt(

                    inputText = readln(),
                    invalidMessage = "Please Enter Valid Unsigned Integer"
                ),
                thresholdValue = thresholdValue,
                constructInvalidMessage = constructInvalidMessage
            )
        }
    }

    @JvmStatic
    fun getValidDateTimeInNormalPattern(promptPrefix: String = ""): String {

        // TODO : Implement Back
        print("Enter ${promptPrefix}Time (DD/MM/YYYY HH:MM:SS) : ")
        return try {

            LocalDateTime.parse(readlnOrNull(), DateTimeUtils.normalDateTimePattern)
                .format(DateTimeUtils.normalDateTimePattern)

        } catch (e: DateTimeParseException) {

            println("Invalid Date...")
            getValidDateTimeInNormalPattern()
        }
    }

    @JvmStatic
    fun getValidDateInNormalPattern(promptPrefix: String = ""): String {

        // TODO : Implement Back
        print("Enter ${promptPrefix}Date (DD/MM/YYYY) : ")
        return try {

            LocalDate.parse(readlnOrNull().toString(), DateTimeUtils.normalDatePattern)
                .format(DateTimeUtils.normalDatePattern)

        } catch (e: DateTimeParseException) {

            println("Invalid Date...")
            getValidDateInNormalPattern(promptPrefix = promptPrefix)
        }
    }

    @JvmStatic
    fun getValidDateInNormalFormat(promptPrefix: String = ""): LocalDate {

        // TODO : Implement Back
        print("Enter ${promptPrefix}Date (DD/MM/YYYY) : ")
        return try {

            LocalDate.parse(readlnOrNull().toString(), DateTimeUtils.normalDatePattern)

        } catch (e: DateTimeParseException) {

            println("Invalid Date...")
            getValidDateInNormalFormat(promptPrefix = promptPrefix)
        }
    }
}
