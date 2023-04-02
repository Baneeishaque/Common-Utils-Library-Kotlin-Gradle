package common.utils.library.utils

class CommandLinePrintMenuWithBackPrompt(private val commandLinePrintMenu: CommandLinePrintMenuInterface) {
    fun printMenuWithBackPromptFromListOfCommands(listOfCommands: List<String>) {

        commandLinePrintMenu.printMenuFromListOfCommands(listOfCommands = listOfCommands, promptWord = "Back")
    }
}