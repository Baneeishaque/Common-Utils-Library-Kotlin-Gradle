package common.utils.library.utils

import common.utils.library.models.IsOkModel

object IsOkUtils {

    @JvmStatic
    fun <T> handleIsOkObject(

        isOkModel: IsOkModel<T>,
        dataOperation: (T) -> Unit,
        errorOperation: (String) -> Unit
    ): Unit = if (isOkModel.isOK) {

        dataOperation.invoke(isOkModel.data!!)

    } else {

        errorOperation.invoke(isOkModel.error!!)
    }

    @JvmStatic
    fun <T> isNotOk(

        isOkModel: IsOkModel<T>

    ): Boolean = !isOkModel.isOK

    @JvmStatic
    fun <T> isOkHandler(

        isOkModel: IsOkModel<*>,
        dataOnFailure: T? = null,
        successActions: () -> T,
        failureActions: () -> Unit = fun() {}

    ): T? {

        var localData: T? = dataOnFailure
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

                return IsOkModel(isOK = false, error = isOkModel.error, errorSpecifier = isOkModel.errorSpecifier)
            }
        }
        return IsOkModel(isOK = true, data = result)
    }
}
