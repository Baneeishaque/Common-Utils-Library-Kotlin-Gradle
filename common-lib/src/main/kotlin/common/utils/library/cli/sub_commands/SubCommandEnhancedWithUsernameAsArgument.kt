package common.utils.library.cli.sub_commands

import common.utils.library.enums.CommandLineApiMethodCommonArgumentsEnum
import common.utils.library.enums.EnvironmentFileEntryCommonEnum
import common.utils.library.utils.ApiUtilsCommon
import common.utils.library.utils.ApiUtilsInteractiveCommon
import io.github.cdimascio.dotenv.Dotenv

abstract class SubCommandEnhancedWithUsernameAsArgument(

    name: String,
    actionDescription: String,
    open val isDevelopmentMode: Boolean,
    open val dotEnv: Dotenv
) :
    SubCommandEnhanced(
        name = name,
        actionDescription = actionDescription
    ) {

    val username: String? = getOptionalTextArgument(
        fullName = CommandLineApiMethodCommonArgumentsEnum.Username.name,
        description = "Username of the User"
    )

    override fun execute() {

        beforeExecuteActions()

        if (isDevelopmentMode) {

            println("userName = $username")
        }

        if (username.isNullOrEmpty()) {

            val environmentUsername = dotEnv[EnvironmentFileEntryCommonEnum.USER_NAME.name]
            if (environmentUsername.isNullOrEmpty()) {

                ApiUtilsInteractiveCommon.printMissingArgumentMessageForApi(argumentSummary = "username of the user")

            } else {

                furtherActions(usernameLocal = environmentUsername)
            }
        } else {

            furtherActions(usernameLocal = username)
        }
    }

    abstract fun beforeExecuteActions()

    abstract fun furtherActions(usernameLocal: String)
}
