package common.utils.library.utils

import common.utils.library.models.IsOkModel
import io.github.cdimascio.dotenv.Dotenv
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

open class EnvironmentVariableForAny<T>(val isAvailable: Boolean, val value: T? = null)

class EnvironmentVariableForWholeNumber(isAvailable: Boolean, value: UInt? = null) :
    EnvironmentVariableForAny<UInt>(isAvailable, value)

class EnvironmentVariableForBoolean(isAvailable: Boolean, value: Boolean? = null) :
    EnvironmentVariableForAny<Boolean>(isAvailable, value)

object EnvironmentFileOperations {

    @JvmStatic
    fun getEnvironmentVariableValueForTextWithDefaultValue(

        dotenv: Dotenv,
        environmentVariableName: String,
        defaultValue: String

    ): String = (dotenv[environmentVariableName] ?: defaultValue).trim('\'')

    @JvmStatic
    fun getEnvironmentVariableValueForWholeNumberWithDefaultValueInteractive(

        dotenv: Dotenv,
        environmentVariableName: String,
        environmentVariableFormalName: String,
        defaultValue: UInt

    ): EnvironmentVariableForWholeNumber {

        val result: String? = dotenv[environmentVariableName]
        return if (result.isNullOrEmpty()) {

            EnvironmentVariableForWholeNumber(isAvailable = true, value = defaultValue)

        } else {

            try {

                EnvironmentVariableForWholeNumber(isAvailable = true, value = result.toUInt())

            } catch (exception: NumberFormatException) {

                println("Invalid $environmentVariableFormalName (Environment File)")
                EnvironmentVariableForWholeNumber(isAvailable = false, value = defaultValue)
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

        dotenv: Dotenv,
        environmentVariableName: String,
        environmentVariableFormalName: String? = null

    ): EnvironmentVariableForWholeNumber {

        val result: String? = dotenv[environmentVariableName]
        return if (result.isNullOrEmpty()) {

            println("Please specify ${environmentVariableFormalName ?: environmentVariableName} (Environment File)")
            EnvironmentVariableForWholeNumber(isAvailable = false)

        } else {

            try {

                EnvironmentVariableForWholeNumber(isAvailable = true, value = result.toUInt())

            } catch (exception: NumberFormatException) {

                println("Invalid $environmentVariableFormalName (Environment File)")
                EnvironmentVariableForWholeNumber(isAvailable = false)
            }
        }
    }

    @JvmStatic
    fun isEnvironmentVariablesAreAvailable(environmentVariables: List<EnvironmentVariableForAny<*>>): Boolean {

        return environmentVariables.all { it.isAvailable }
    }

    @JvmStatic
    fun confirmWholeNumberEnvironmentVariableData(

        dotenv: Dotenv,
        environmentVariableName: String,
        dataCorrectionOperation: () -> IsOkModel<UInt>,
        dataSpecification: String

    ): IsOkModel<UInt> {

        val environmentVariableForWholeNumber: EnvironmentVariableForWholeNumber =
            getEnvironmentVariableValueForWholeNumberInteractive(

                dotenv = dotenv,
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
}
