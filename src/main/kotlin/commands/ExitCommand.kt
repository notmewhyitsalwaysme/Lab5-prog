package commands

import input.InputManager

/**
 * Завершает программу без сохранения. Теперь это Callback
 */
class ExitCommand(
    private val console: InputManager,
) : Command {
    override val name = "exit"
    override val description = "завершить программу"

    override fun execute(args: List<String>) {
        console.print("До свидания!")
    }
}
