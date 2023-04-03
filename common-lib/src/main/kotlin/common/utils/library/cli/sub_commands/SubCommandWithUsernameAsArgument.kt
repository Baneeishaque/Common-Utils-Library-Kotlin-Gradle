package common.utils.library.cli.sub_commands

import common.utils.library.enums.CommandLineApiMethodCommonArgumentsEnum
import common.utils.library.enums.EnvironmentFileEntryCommonEnum
import common.utils.library.utils.ApiUtils.printMissingArgumentMessageForApi
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.optional

@OptIn(ExperimentalCli::class)
abstract class SubCommandWithUsernameAsArgument(

    name: String,
    actionDescription: String,
    open val isDevelopmentMode: Boolean,
    open val dotEnv: Dotenv
) :
    Subcommand(
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

                printMissingArgumentMessageForApi(argumentSummary = "username of the user")

            } else {

                furtherActions(usernameLocal = environmentUsername)
            }
        } else {

            furtherActions(usernameLocal = username)
        }
    }

    abstract fun beforeExecuteActions()

    abstract fun furtherActions(usernameLocal: String)

    fun getOptionalTextArgument(fullName: String, description: String): String? {

        val optionalTextArgument: String? by argument(

            type = ArgType.String,
            fullName = fullName,
            description = description

        ).optional()

        return optionalTextArgument
    }
}