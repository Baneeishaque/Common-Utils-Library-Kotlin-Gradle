package common.utils.library.utils

import common.utils.library.models.IsOkModel
import io.github.cdimascio.dotenv.Dotenv
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

object EnvironmentFileOperationsInteractive {

    @JvmStatic
    fun getEnvironmentVariableValueForWholeNumberWithDefaultValueInteractive(

        dotEnv: Dotenv,
        environmentVariableName: String,
        environmentVariableFormalName: String,
        defaultValue: UInt

    ): EnvironmentVariableForWholeNumber {

        val result: String? = dotEnv[environmentVariableName]
        return if (result.isNullOrEmpty()) {

            EnvironmentVariableForWholeNumber(

                isAvailable = true,
                value = defaultValue
            )

        } else {

            try {

                EnvironmentVariableForWholeNumber(

                    isAvailable = true,
                    value = result.toUInt()
                )

            } catch (exception: NumberFormatException) {

                println("Invalid $environmentVariableFormalName (Environment File)")
                EnvironmentVariableForWholeNumber(

                    isAvailable = false,
                    value = defaultValue
                )
            }
        }
    }

    @JvmStatic
    fun getEnvironmentVariableValueForBooleanWithDefaultValueInteractive(

        dotEnv: Dotenv,
        environmentVariableName: String,
        environmentVariableFormalName: String = "",
        defaultValue: Boolean

    ): EnvironmentVariableForBoolean {

        val result: String? = dotEnv[environmentVariableName]
        return if (result.isNullOrEmpty()) {

            EnvironmentVariableForBoolean(isAvailable = true, value = defaultValue)

        } else {

            try {

                EnvironmentVariableForBoolean(isAvailable = true, value = result.toBooleanStrict())

            } catch (exception: IllegalArgumentException) {

                println("Invalid ${environmentVariableFormalName.ifEmpty { environmentVariableName }} (Environment File)")
                EnvironmentVariableForBoolean(isAvailable = false, value = defaultValue)
            }
        }
    }

    @JvmStatic
    fun getEnvironmentVariableValueForWholeNumberInteractive(

        dotEnv: Dotenv,
        environmentVariableName: String,
        environmentVariableFormalName: String? = null

    ): EnvironmentVariableForWholeNumber {

        val result: String? = dotEnv[environmentVariableName]
        return if (result.isNullOrEmpty()) {

            println("Please specify ${environmentVariableFormalName ?: environmentVariableName} (Environment File)")
            EnvironmentVariableForWholeNumber(isAvailable = false)

        } else {

            try {

                EnvironmentVariableForWholeNumber(isAvailable = true, value = result.toUInt())

            } catch (_: NumberFormatException) {

                println("Invalid $environmentVariableFormalName (Environment File)")
                EnvironmentVariableForWholeNumber(isAvailable = false)
            }
        }
    }

    @JvmStatic
    fun confirmWholeNumberEnvironmentVariableData(

        dotEnv: Dotenv,
        environmentVariableName: String,
        dataCorrectionOperation: () -> IsOkModel<UInt>,
        dataSpecification: String

    ): IsOkModel<UInt> {

        val environmentVariableForWholeNumber: EnvironmentVariableForWholeNumber =
            getEnvironmentVariableValueForWholeNumberInteractive(

                dotEnv = dotEnv,
                environmentVariableName = environmentVariableName
            )

        val confirmDataResult: IsOkModel<UInt> = if (environmentVariableForWholeNumber.isAvailable) {

            InputUtilsInteractive.confirmDataInteractive(

                dataSpecification = dataSpecification,
                data = environmentVariableForWholeNumber.value!!,
                dataCorrectionFunction = dataCorrectionOperation
            )
        } else {

            dataCorrectionOperation.invoke()
        }
        return confirmDataResult
    }

    @JvmStatic
    fun confirmWholeNumberEnvironmentVariableDataOrBackWithPrompt(

        dotEnv: Dotenv,
        environmentVariableName: String,
        dataSpecification: String

    ): IsOkModel<UInt> {

        return confirmWholeNumberEnvironmentVariableData(

            dotEnv = dotEnv,
            environmentVariableName = environmentVariableName,
            dataCorrectionOperation = fun(): IsOkModel<UInt> {

                return InputUtilsInteractive.getValidUnsignedIntOrBackWithPrompt(

                    dataSpecification = dataSpecification,
                )
            },
            dataSpecification = dataSpecification
        )
    }
}
