package commands

import runner.CommandInvoker

/**
 * Выводит справку по всем доступным командам.
 * @property invoker инвокер, из которого берётся реестр команд
 */
class HelpCommand(private val invoker: CommandInvoker) : Command {
    override val name = "help"
    override val description = "вывести справку по доступным командам"

    override fun execute(args: List<String>) {
        invoker.getCommands().values
            .sortedBy { it.name }
            .forEach { println("  ${it.name.padEnd(40)} — ${it.description}") }
    }
}
