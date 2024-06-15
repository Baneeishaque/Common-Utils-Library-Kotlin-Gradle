package common.utils.library.utils

import common.utils.library.models.FailureBasedOnIsOkModel
import common.utils.library.models.FailureWithoutExplanationBasedOnIsOkModel
import common.utils.library.models.IsOkModel
import common.utils.library.models.SuccessBasedOnIsOkModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

object InputUtilsInteractive {

    @JvmStatic
    fun getValidFloat(

        inputText: String,
        invalidMessage: String = "Please Enter Valid Floating Point Integer"

    ): Float {

        val getValidFloatResult: Pair<Boolean, Float?> = InputUtils.getValidFloat(inputText = inputText)
        if (getValidFloatResult.first) {

            return getValidFloatResult.second!!

        } else {

            println(invalidMessage)
            return getValidFloat(

                inputText = readln(),
                invalidMessage = invalidMessage
            )
        }
    }

    @JvmStatic
    fun getValidFloat(

        inputText: String,
        constructInvalidMessage: (String) -> String

    ): Float {

        val getValidFloatResult: Pair<Boolean, Float?> = InputUtils.getValidFloat(inputText = inputText)
        if (getValidFloatResult.first) {

            return getValidFloatResult.second!!

        } else {

            print(constructInvalidMessage.invoke(inputText))
            return getValidFloat(

                inputText = readln(),
                constructInvalidMessage = constructInvalidMessage
            )
        }
    }

    @JvmStatic
    fun getValidUnsignedInt(

        inputText: String,
        invalidMessage: String = "Please Enter Valid Unsigned Integer"

    ): UInt = try {

        inputText.toUInt()

    } catch (exception: NumberFormatException) {

        print(message = invalidMessage)
        getValidUnsignedInt(

            inputText = readln(),
            invalidMessage = invalidMessage
        )
    }

    @JvmStatic
    fun getValidUnsignedIntOrBack(

        inputText: String,
        invalidMessage: String = "Please Enter Valid Unsigned Integer",
        backIndicator: String = "B"

    ): IsOkModel<UInt> {

        try {

            return SuccessBasedOnIsOkModel(

                ownData = inputText.toUInt()
            )

        } catch (_: NumberFormatException) {

            return if (inputText == backIndicator) {

                FailureWithoutExplanationBasedOnIsOkModel()

            } else {

                print(message = "$invalidMessage Or $backIndicator to back : ")
                getValidUnsignedIntOrBack(

                    inputText = readln(),
                    invalidMessage = invalidMessage,
                    backIndicator = backIndicator
                )
            }
        }
    }

    @JvmStatic
    fun getValidUnsignedIntOrBackWithPrompt(

        dataSpecification: String,
        dataIndex: UInt? = null,
        backIndicator: String = "B"

    ): IsOkModel<UInt> {

        print("Enter $dataSpecification ${if (dataIndex == null) "" else "($dataIndex) "} Or $backIndicator to back : ")
        return getValidUnsignedIntOrBack(

            inputText = readln(),
            invalidMessage = "Please Enter Valid $dataSpecification",
            backIndicator = backIndicator
        )
    }

    @JvmStatic
    fun getGreaterUnsignedInt(

        inputUInt: UInt,
        thresholdValue: UInt,
        constructInvalidMessage: (UInt) -> String

    ): UInt {

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

            LocalDateTime.parse(readlnOrNull().toString(), DateTimeUtils.normalDateTimePattern)
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

    @JvmStatic
    fun <T> confirmDataInteractive(

        dataSpecification: String,
        data: T,
        dataCorrectionFunction: () -> IsOkModel<T>

    ): IsOkModel<T> {

        var localData: T = data
        do {
            print("Please confirm $dataSpecification (Y/N or B for Back) -> $localData : ")
            when (readlnOrNull().toString()) {

                "Y", "" -> {
                    break
                }

                "N" -> {
                    val localDataResult: IsOkModel<T> = dataCorrectionFunction.invoke()
                    if (localDataResult.isOK) {

                        localData = localDataResult.data!!

                    } else {

                        if (localDataResult.error != null) {

                            return FailureBasedOnIsOkModel(ownError = localDataResult.error!!)
                        }
                        return FailureWithoutExplanationBasedOnIsOkModel()
                    }
                }

                "B" -> {
                    return FailureWithoutExplanationBasedOnIsOkModel()
                }

                else -> ErrorUtilsInteractive.printInvalidOptionMessage()
            }
        } while (true)

        return SuccessBasedOnIsOkModel(

            ownData = localData
        )
    }

    @JvmStatic
    fun getMultipleValidUnsignedIntOrBackWithPrompt(

        dataSpecification: String,
        count: UInt

    ): IsOkModel<List<UInt>> {

        val inputs: MutableList<UInt> = mutableListOf()
        repeat(count.toInt()) { index: Int ->

            val inputResult: IsOkModel<UInt> =
                getValidUnsignedIntOrBackWithPrompt(

                    dataSpecification = dataSpecification,
                    dataIndex = (index + 1).toUInt(),
                )
            if (inputResult.isOK) {

                inputs.add(inputResult.data!!)

            } else {

                return FailureWithoutExplanationBasedOnIsOkModel()
            }
        }
        return SuccessBasedOnIsOkModel(ownData = inputs)
    }
}
