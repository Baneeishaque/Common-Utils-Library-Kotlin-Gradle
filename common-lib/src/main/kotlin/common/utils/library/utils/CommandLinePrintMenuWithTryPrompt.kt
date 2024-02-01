package common.utils.library.utils

class CommandLinePrintMenuWithTryPrompt(private val commandLinePrintMenu: CommandLinePrintMenuInterface) {

    fun printMenuWithTryPromptFromListOfCommands(listOfCommands: List<String>) {

        commandLinePrintMenu.printMenuFromListOfCommands(listOfCommands = listOfCommands, promptWord = "Try")
    }
}