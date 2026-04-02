package commands

import input.IOManager
import runner.CommandInvoker

/**
 * Выводит последние 12 команд без аргументов.
 * @property invoker инвокер с историей
 */
class HistoryCommand(
    private val invoker: CommandInvoker,
    private val inputManager: IOManager
) : Command {
    override val name = "history"
    override val description = "вывести последние 12 команд"

    override fun execute(args: List<String>) {
        val history = invoker.getHistory()
        if (history.isEmpty()) {
            inputManager.print("История пуста.")
            return
        }
        history.forEachIndexed { i, cmd -> inputManager.print("  ${i + 1}. $cmd") }
    }
}
