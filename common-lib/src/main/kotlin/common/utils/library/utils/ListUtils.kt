package common.utils.library.utils

object ListUtils {

    @JvmStatic
    fun indexedListTextFromList(list: List<*>): String {

        var result = ""
        list.forEachIndexed { index: Int, listElement: Any? ->
            result += "Option }${index + 1} - ${

                listElement.toString().trim()
            }\n"
        }
        return result
    }

    @JvmStatic
    fun getValidIndexWithSelectionPromptForNonCollections(

        list: List<*>,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String

    ): UInt = getValidIndexWithSelectionPrompt(

        list = list,
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = false
    )

    @JvmStatic
    fun getValidIndexWithSelectionPrompt(

        list: List<*>,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String,
        isCollection: Boolean

    ): UInt = getValidIndexWithSelectionPromptAndOptionalBackValue(

        inclusionCheckFunction = fun(inputForIndex: UInt): Boolean {

            return inputForIndex.toInt() in (list.indices + 1)
        },
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = isCollection
    )

    @JvmStatic
    fun getValidIndexWithSelectionPrompt(

        inclusionCheckFunction: (UInt) -> Boolean,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String,
        isCollection: Boolean

    ): UInt = getValidIndexWithSelectionPromptAndOptionalBackValue(

        inclusionCheckFunction = inclusionCheckFunction,
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = isCollection
    )

    @JvmStatic
    fun getValidIndexWithSelectionPromptAndOptionalBackValue(

        inclusionCheckFunction: (UInt) -> Boolean,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String,
        isCollection: Boolean,
        backValue: UInt? = null

    ): UInt {

        CommandLinePrintMenuWithEnterPrompt(CommandLinePrintMenu()).printMenuWithEnterPromptFromListOfCommands(

            listOfCommands = listOf(
                "\n${itemSpecification}${if (isCollection) "s" else ""}",
                items,
                "Enter $itemSpecificationPrefix$itemSpecification Index${if (backValue != null) ", or $backValue to back" else ""} : ${if (isCollection) itemSpecification.first() else "Option "}"
            )
        )

        return getValidIndexWithOptionalBackValue(

            inclusionCheckFunction = inclusionCheckFunction,
            inputForIndex = readln(),
            itemSpecification = itemSpecification,
            items = items,
            isCollection = isCollection,
            backValue = backValue
        )
    }

    @JvmStatic
    fun getValidIndexFromCollectionWithZeroAsBack(

        map: Map<UInt, *>,
        inputForIndex: String,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String

    ): UInt = getValidIndexWithZeroAsBack(

        map = map,
        inputForIndex = inputForIndex,
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = true
    )

    @JvmStatic
    fun getValidIndexWithZeroAsBack(

        map: Map<UInt, *>,
        inputForIndex: String,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String,
        isCollection: Boolean

    ): UInt = getValidIndexWithOptionalBackValue(

        map = map,
        inputForIndex = inputForIndex,
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = isCollection,
        backValue = 0u
    )

    @JvmStatic
    fun getValidIndexWithOptionalBackValue(

        map: Map<UInt, *>,
        inputForIndex: String,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String,
        isCollection: Boolean,
        backValue: UInt? = null

    ): UInt = getValidIndexWithOptionalBackValue(

        inclusionCheckFunction = fun(inputForIndex: UInt): Boolean {

            return map.containsKey(inputForIndex)
        },
        inputForIndex = inputForIndex,
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = isCollection,
        backValue = backValue
    )

    @JvmStatic
    fun getValidIndexWithOptionalBackValue(

        inclusionCheckFunction: (UInt) -> Boolean,
        inputForIndex: String,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String,
        isCollection: Boolean,
        backValue: UInt? = null

    ): UInt {

        return if ((backValue != null) && (inputForIndex == backValue.toString())) {

            backValue

        } else {

            getValidIndexWithOptionalBackValue(

                inclusionCheckFunction = inclusionCheckFunction,
                inputForIndex = inputForIndex.toUInt(),
                itemSpecificationPrefix = itemSpecificationPrefix,
                itemSpecification = itemSpecification,
                items = items,
                isCollection = isCollection,
                backValue = backValue
            )
        }
    }

    @JvmStatic
    fun getValidIndexWithOptionalBackValue(

        inclusionCheckFunction: (UInt) -> Boolean,
        inputForIndex: UInt,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String,
        isCollection: Boolean,
        backValue: UInt? = null

    ): UInt {

        if (inclusionCheckFunction(inputForIndex)) {

            return inputForIndex

        } else {

            println("Invalid $itemSpecification Index, Try again ? (Y/N) : ")
            return when (readlnOrNull()) {

                "Y", "" -> {

                    getValidIndexWithSelectionPromptAndOptionalBackValue(

                        inclusionCheckFunction = inclusionCheckFunction,
                        itemSpecificationPrefix = itemSpecificationPrefix,
                        itemSpecification = itemSpecification,
                        items = items,
                        isCollection = isCollection,
                        backValue = backValue
                    )
                }

                "N" -> {

                    backValue
                        ?: getValidIndexWithSelectionPromptAndOptionalBackValue(

                            inclusionCheckFunction = inclusionCheckFunction,
                            itemSpecificationPrefix = itemSpecificationPrefix,
                            itemSpecification = itemSpecification,
                            items = items,
                            isCollection = isCollection
                        )
                }

                else -> {

                    println("Invalid Entry...")
                    getValidIndexWithSelectionPromptAndOptionalBackValue(

                        inclusionCheckFunction = inclusionCheckFunction,
                        itemSpecificationPrefix = itemSpecificationPrefix,
                        itemSpecification = itemSpecification,
                        items = items,
                        isCollection = isCollection,
                        backValue = backValue
                    )
                }
            }
        }
    }

    @JvmStatic
    fun getValidIndexFromCollectionWithSelectionPromptAndOptionalBackValue(

        inclusionCheckFunction: (UInt) -> Boolean,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String,
        backValue: UInt? = null

    ): UInt = getValidIndexWithSelectionPromptAndOptionalBackValue(

        inclusionCheckFunction = inclusionCheckFunction,
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = true,
        backValue = backValue,
    )

    @JvmStatic
    fun getValidIndexWithSelectionPromptAndZeroAsBack(

        inclusionCheckFunction: (UInt) -> Boolean,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String,
        isCollection: Boolean

    ): UInt = getValidIndexWithSelectionPromptAndOptionalBackValue(

        inclusionCheckFunction = inclusionCheckFunction,
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = isCollection,
        backValue = 0u,
    )

    @JvmStatic
    fun getValidIndexFromCollectionWithSelectionPromptAndZeroAsBack(

        inclusionCheckFunction: (UInt) -> Boolean,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String

    ): UInt = getValidIndexWithSelectionPromptAndZeroAsBack(

        inclusionCheckFunction = inclusionCheckFunction,
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = true
    )

    @JvmStatic
    fun getValidIndexFromCollectionWithSelectionPromptAndZeroAsBack(

        map: Map<UInt, *>,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String

    ): UInt = getValidIndexWithSelectionPromptAndZeroAsBack(

        inclusionCheckFunction = fun(inputForIndex: UInt): Boolean {

            return map.containsKey(inputForIndex)
        },
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = true
    )

    @JvmStatic
    fun getValidIndexFromCollectionWithSelectionPromptAndZeroAsBack(

        list: List<*>,
        itemSpecificationPrefix: String = "",
        itemSpecification: String,
        items: String

    ): UInt = getValidIndexWithSelectionPromptAndZeroAsBack(

        inclusionCheckFunction = fun(inputForIndex: UInt): Boolean {

            return inputForIndex.toInt() in (list.indices + 1)
        },
        itemSpecificationPrefix = itemSpecificationPrefix,
        itemSpecification = itemSpecification,
        items = items,
        isCollection = true
    )
}