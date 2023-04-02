package common.utils.library.utils

class CommandLinePrintMenu : CommandLinePrintMenuInterface {

    override fun printMenuFromListOfCommands(listOfCommands: List<String>, promptWord: String) {

        listOfCommands.forEach { command ->

            if (promptWord.isEmpty() || (!(command.contains(promptWord, ignoreCase = true)))) {

                println(command)

            } else if (command.isNotBlank()) {

                print(command)
            }
        }
    }
}