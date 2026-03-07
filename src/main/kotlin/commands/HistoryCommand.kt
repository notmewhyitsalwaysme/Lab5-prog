package commands

import runner.CommandInvoker

/**
 * Выводит последние 12 команд без аргументов.
 * @property invoker инвокер с историей
 */
class HistoryCommand(private val invoker: CommandInvoker) : Command {
    override val name = "history"
    override val description = "вывести последние 12 команд"

    override fun execute(args: List<String>) {
        val history = invoker.getHistory()
        if (history.isEmpty()) {
            println("История пуста.")
            return
        }
        history.forEachIndexed { i, cmd -> println("  ${i + 1}. $cmd") }
    }
}
