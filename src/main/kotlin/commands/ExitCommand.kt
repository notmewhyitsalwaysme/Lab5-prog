package commands

/**
 * Завершает программу без сохранения. Теперь это Callback
 */
class ExitCommand(private val onExit: () -> Unit) : Command {
    override val name = "exit"
    override val description = "завершить программу"

    override fun execute(args: List<String>) {
        println("До свидания!")
        onExit()
    }
}
