package common.utils.library.cli.sub_commands

import common.utils.library.enums.CommandLineApiMethodCommonArgumentsEnum
import common.utils.library.enums.EnvironmentFileEntryCommonEnum
import common.utils.library.utils.ApiUtilsCommon
import common.utils.library.utils.ApiUtilsInteractiveCommon
import io.github.cdimascio.dotenv.Dotenv
import kotlinx.cli.ArgType
import kotlinx.cli.optional

abstract class SubCommandEnhancedWithUserIdAsArgument(

    name: String,
    actionDescription: String,
    open val isDevelopmentMode: Boolean,
    open val dotEnv: Dotenv
) :
    SubCommandEnhanced(
        name = name,
        actionDescription = actionDescription
    ) {

    private val userIdDescription: String = "User Id of the User"
    val userId: Int? by argument(

        type = ArgType.Int,
        fullName = CommandLineApiMethodCommonArgumentsEnum.UserId.name,
        description = userIdDescription

    ).optional()

    override fun execute() {

        beforeExecuteActions()

        if (isDevelopmentMode) {

            println("userId = $userId")
        }

        if (userId == null) {

            val environmentUserId = dotEnv[EnvironmentFileEntryCommonEnum.USER_ID.name]
            if (environmentUserId.isNullOrEmpty()) {

                ApiUtilsInteractiveCommon.printMissingArgumentMessageForApi(argumentSummary = userIdDescription)

            } else {

                try {
                    if (environmentUserId.toInt() <= 0) {

                        ApiUtilsInteractiveCommon.printNegativeNumberOrZeroArgumentValueMessageForApi(argumentSummary = userIdDescription)

                    } else {

                        furtherActions(userIdLocal = environmentUserId.toInt())
                    }

                } catch (_: NumberFormatException) {

                    ApiUtilsInteractiveCommon.printInvalidArgumentValueMessageForApi(argumentSummary = userIdDescription)
                }
            }
        } else {

            furtherActions(userIdLocal = userId!!)
        }
    }

    abstract fun beforeExecuteActions()

    abstract fun furtherActions(userIdLocal: Int)
}
