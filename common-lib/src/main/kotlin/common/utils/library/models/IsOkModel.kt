package common.utils.library.models

data class IsOkModel<T>(val isOK: Boolean, val data: T? = null, val error: String? = null)
