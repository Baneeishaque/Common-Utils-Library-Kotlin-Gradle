package common.utils.library.cli.sub_commands

import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.optional

@OptIn(ExperimentalCli::class)
abstract class SubCommandEnhanced(

    name: String,
    actionDescription: String

) : Subcommand(

    name = name,
    actionDescription = actionDescription
) {

    fun getOptionalTextArgument(fullName: String, description: String): String? {

        val optionalTextArgument: String? by argument(

            type = ArgType.String,
            fullName = fullName,
            description = description

        ).optional()

        return optionalTextArgument
    }
}
