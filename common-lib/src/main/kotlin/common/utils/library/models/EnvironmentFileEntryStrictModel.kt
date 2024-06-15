package common.utils.library.models

class EnvironmentFileEntryStrictModel<E : Enum<*>>(

    override val entry: E,
    override val formalName: String

) : EnvironmentFileEntryModel<E>(entry, formalName)
