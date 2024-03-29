package common.utils.library.models

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class CommonDataModel<T>(

    @Required val status: Int,
    val data: List<T>? = null,
    val error: String? = null,
    val textData: String? = null
)
