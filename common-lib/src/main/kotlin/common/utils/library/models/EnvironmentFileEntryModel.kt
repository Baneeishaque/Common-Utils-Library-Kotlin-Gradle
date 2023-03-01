package common.utils.library.models

class EnvironmentFileEntryModel<E : Enum<E>>(val entryName: E, val entryFormalName: String? = null)