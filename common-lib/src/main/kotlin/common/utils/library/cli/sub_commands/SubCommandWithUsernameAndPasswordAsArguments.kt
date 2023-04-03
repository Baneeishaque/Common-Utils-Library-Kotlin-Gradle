package common.utils.library.cli.sub_commands

import common.utils.library.enums.CommandLineApiMethodCommonArgumentsEnum
import common.utils.library.enums.EnvironmentFileEntryCommonEnum
import common.utils.library.utils.ApiUtils.printMissingArgumentMessageForApi
import io.github.cdimascio.dotenv.Dotenv

abstract class SubCommandWithUsernameAndPasswordAsArguments(

    name: String,
    actionDescription: String,
    override val isDevelopmentMode: Boolean,
    override val dotEnv: Dotenv
) :
    SubCommandWithUsernameAsArgument(

        name = name,
        actionDescription = actionDescription,
        isDevelopmentMode = isDevelopmentMode,
        dotEnv = dotEnv
    ) {

    val password: String? = getOptionalTextArgument(
        fullName = CommandLineApiMethodCommonArgumentsEnum.Password.name,
        description = "Password of the User"
    )

    abstract fun localBeforeExecuteActions()

    override fun beforeExecuteActions() {

        if (isDevelopmentMode) {

            println("userName = $username")
        }
        localBeforeExecuteActions()
    }

    override fun furtherActions(usernameLocal: String) {

        if (password.isNullOrEmpty()) {

            val environmentPasscode = dotEnv[EnvironmentFileEntryCommonEnum.PASSWORD.name]
            if (environmentPasscode.isNullOrEmpty()) {

                printMissingArgumentMessageForApi(argumentSummary = "password of the user")

            } else {

                furtherActions(
                    usernameLocal = usernameLocal,
                    passwordLocal = environmentPasscode
                )
            }

        } else {

            furtherActions(
                usernameLocal = usernameLocal,
                passwordLocal = password,
            )
        }
    }

    abstract fun furtherActions(usernameLocal: String, passwordLocal: String)
}