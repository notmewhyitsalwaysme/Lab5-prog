package commands

import kotlin.system.exitProcess

/**
 * Завершает программу без сохранения.
 */
class ExitCommand : Command {
    override val name = "exit"
    override val description = "завершить программу (без сохранения)"

    override fun execute(args: List<String>) {
        println("Завершение работы.")
        exitProcess(0) //nizya
    }
}
