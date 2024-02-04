package common.utils.library.utils

import common.utils.library.models.IsOkModel

object IsOkUtils {

    @JvmStatic
    fun <T> printIsOkObject(

        isOkModel: IsOkModel<T>

    ) = handleIsOkObject(

        isOkModel = isOkModel,
        dataOperation = fun(data: T) {

            println(data)
        },
        errorOperation = fun(error: String) {

            println(error)
        }
    )

    @JvmStatic
    fun <T> handleIsOkObject(

        isOkModel: IsOkModel<T>,
        dataOperation: (T) -> Unit,
        errorOperation: (String) -> Unit

    ) {
        if (isOkModel.isOK) {

            dataOperation.invoke(isOkModel.data!!)

        } else {

            errorOperation.invoke(isOkModel.error!!)
        }
    }
}