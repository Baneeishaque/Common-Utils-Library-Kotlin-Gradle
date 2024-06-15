package common.utils.library.cli.sub_commands

import common.utils.library.enums.CommandLineApiMethodCommonArgumentsEnum
import common.utils.library.enums.EnvironmentFileEntryCommonEnum
import common.utils.library.utils.ApiUtilsCommon
import common.utils.library.utils.ApiUtilsInteractiveCommon
import io.github.cdimascio.dotenv.Dotenv

abstract class SubCommandEnhancedWithUsernameAndPasswordAsArguments(

    name: String,
    actionDescription: String,
    override val isDevelopmentMode: Boolean,
    override val dotEnv: Dotenv
) :
    SubCommandEnhancedWithUsernameAsArgument(

        name = name,
        actionDescription = actionDescription,
        isDevelopmentMode = isDevelopmentMode,
        dotEnv = dotEnv
    ) {

    val password: String? = getOptionalTextArgument(
        fullName = CommandLineApiMethodCommonArgumentsEnum.Password.name,
        description = "Password of the User"
    )

    abstract fun additionalBeforeExecuteActions()

    override fun beforeExecuteActions() {

        if (isDevelopmentMode) {

            println("userName = $username")
        }
        additionalBeforeExecuteActions()
    }

    override fun furtherActions(usernameLocal: String) {

        if (password.isNullOrEmpty()) {

            val environmentPasscode = dotEnv[EnvironmentFileEntryCommonEnum.PASSWORD.name]
            if (environmentPasscode.isNullOrEmpty()) {

                ApiUtilsInteractiveCommon.printMissingArgumentMessageForApi(argumentSummary = "password of the user")

            } else {

                additionalFurtherActions(
                    usernameLocal = usernameLocal,
                    passwordLocal = environmentPasscode
                )
            }

        } else {

            additionalFurtherActions(
                usernameLocal = usernameLocal,
                passwordLocal = password,
            )
        }
    }

    abstract fun additionalFurtherActions(usernameLocal: String, passwordLocal: String)
}
