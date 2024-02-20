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

    @JvmStatic
    fun <T> isNotOk(

        isOkModel: IsOkModel<T>

    ): Boolean {

        return !isOkModel.isOK
    }

    @JvmStatic
    fun <T> isOkHandler(

        isOkModel: IsOkModel<*>,
        data: T,
        successActions: () -> T,
        failureActions: () -> Unit = fun() {}

    ): T {

        var localData: T = data
        if (isOkModel.isOK) {

            localData = successActions.invoke()

        } else {

            failureActions.invoke()
        }
        return localData
    }

    @JvmStatic
    fun <T> checkListOfOkModels(isOkModels: List<IsOkModel<T>>): IsOkModel<List<T>> {

        val result: MutableList<T> = mutableListOf()
        for (isOkModel: IsOkModel<T> in isOkModels) {

            if (isOkModel.isOK) {

                result.add(isOkModel.data!!)

            } else {

                return IsOkModel(isOK = false)
            }
        }
        return IsOkModel(isOK = true, data = result)
    }
}