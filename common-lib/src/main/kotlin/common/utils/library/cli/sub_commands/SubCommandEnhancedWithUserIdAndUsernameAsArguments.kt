package common.utils.library.cli.sub_commands

import common.utils.library.enums.CommandLineApiMethodCommonArgumentsEnum
import common.utils.library.enums.EnvironmentFileEntryCommonEnum
import common.utils.library.utils.ApiUtilsCommon
import common.utils.library.utils.ApiUtilsInteractiveCommon
import io.github.cdimascio.dotenv.Dotenv

abstract class SubCommandEnhancedWithUserIdAndUsernameAsArguments(

    name: String,
    actionDescription: String,
    override val isDevelopmentMode: Boolean,
    override val dotEnv: Dotenv

) : SubCommandEnhancedWithUserIdAsArgument(

    name = name,
    actionDescription = actionDescription,
    isDevelopmentMode = isDevelopmentMode,
    dotEnv = dotEnv
) {
    val username: String? = getOptionalTextArgument(

        fullName = CommandLineApiMethodCommonArgumentsEnum.Username.name,
        description = "Username of the User"
    )

    abstract fun additionalBeforeExecuteActions()

    final override fun beforeExecuteActions() {

        if (isDevelopmentMode) {

            println("userId = $userId")
        }
        additionalBeforeExecuteActions()
    }

    final override fun furtherActions(userIdLocal: Int) {

        if (username.isNullOrEmpty()) {

            val envUsername = dotEnv[EnvironmentFileEntryCommonEnum.USER_NAME.name]
            if (envUsername.isNullOrEmpty()) {

                ApiUtilsInteractiveCommon.printMissingArgumentMessageForApi(argumentSummary = "username of the user")

            } else {

                additionalActionsWithUserIdAndUsername(userIdLocal = userIdLocal, usernameLocal = envUsername)
            }
        } else {
            additionalActionsWithUserIdAndUsername(userIdLocal = userIdLocal, usernameLocal = username)
        }
    }

    abstract fun additionalActionsWithUserIdAndUsername(userIdLocal: Int, usernameLocal: String)
}
