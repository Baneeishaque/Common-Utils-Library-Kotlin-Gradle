package common.utils.library.utils

import common.utils.library.models.IsOkModel
import common.utils.library.utils.IsOkUtils.handleIsOkObject

object IsOkUtilsInteractive {

    @JvmStatic
    fun <T> printIsOkObject(

        isOkModel: IsOkModel<T>

    ): Unit = handleIsOkObject(

        isOkModel = isOkModel,
        dataOperation = fun(data: T) {

            println(data)
        },
        errorOperation = fun(error: String) {

            println(error)
        }
    )
}
