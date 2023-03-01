package common.utils.library.utils

object EnumUtils {
    @JvmStatic
    fun getEnumNameForPrint(localEnum: Enum<*>): String {
        return localEnum.name.lowercase().replaceFirstChar { character ->
            character.uppercaseChar()
        }
    }
}