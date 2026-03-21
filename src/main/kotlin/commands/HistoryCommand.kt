package commands

import input.InputManager
import runner.CommandInvoker

/**
 * Выводит последние 12 команд без аргументов.
 * @property invoker инвокер с историей
 */
class HistoryCommand(
    private val invoker: CommandInvoker,
    private val console: InputManager
) : Command {
    override val name = "history"
    override val description = "вывести последние 12 команд"

    override fun execute(args: List<String>) {
        val history = invoker.getHistory()
        if (history.isEmpty()) {
            console.print("История пуста.")
            return
        }
        history.forEachIndexed { i, cmd -> console.print("  ${i + 1}. $cmd") }
    }
}
