package common.utils.library.models

data class ChooseByIdResult<T>(

    val isOkWithData: IsOkModel<T>,
    val id: UInt? = null
)
