package common.utils.library.utils

import io.github.cdimascio.dotenv.Dotenv

open class EnvironmentVariableForAny<T>(

    val isAvailable: Boolean,
    val value: T? = null
)

class EnvironmentVariableForWholeNumber(

    isAvailable: Boolean,
    value: UInt? = null

) : EnvironmentVariableForAny<UInt>(

    isAvailable = isAvailable,
    value = value
)

class EnvironmentVariableForBoolean(

    isAvailable: Boolean,
    value: Boolean? = null

) : EnvironmentVariableForAny<Boolean>(

    isAvailable = isAvailable,
    value = value
)

object EnvironmentFileOperations {

    @JvmStatic
    fun getEnvironmentVariableValueForTextWithDefaultValue(

        dotEnv: Dotenv,
        environmentVariableName: String,
        defaultValue: String

    ): String = (dotEnv[environmentVariableName] ?: defaultValue).trim('\'')

    @JvmStatic
    fun isEnvironmentVariablesAreAvailable(environmentVariables: List<EnvironmentVariableForAny<*>>): Boolean {

        return environmentVariables.all { it.isAvailable }
    }
}
