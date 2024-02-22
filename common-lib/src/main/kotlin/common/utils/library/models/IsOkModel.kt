package common.utils.library.models

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
open class IsOkModel<T>(

    @Required var isOK: Boolean,
    var data: T? = null,
    var error: String? = null
)

@Serializable
class SuccessBasedOnIsOkModel<T>(

    @Required val ownData: T

) : IsOkModel<T>(

    isOK = true,
    data = ownData
)

@Serializable
class FailureBasedOnIsOkModel<T>(

    @Required val ownError: String

) : IsOkModel<T>(

    isOK = false,
    error = ownError
)

@Serializable
class FailureWithoutExplanationBasedOnIsOkModel<T> : IsOkModel<T>(isOK = false)
