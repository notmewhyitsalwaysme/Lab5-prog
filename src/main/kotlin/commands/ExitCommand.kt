package commands

import input.IOManager

/**
 * Завершает программу без сохранения. Теперь это Callback
 */
class ExitCommand(
    private val inputManager: IOManager,
) : Command {
    override val name = "exit"
    override val description = "завершить программу"

    override fun execute(args: List<String>) {
        null
    }
}
