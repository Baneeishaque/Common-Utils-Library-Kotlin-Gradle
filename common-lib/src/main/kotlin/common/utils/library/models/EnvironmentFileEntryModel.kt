package common.utils.library.models

open class EnvironmentFileEntryModel<E : Enum<*>>(

    open val entry: E,
    open val formalName: String? = null
)
