package commands

import collection.CollectionManager
import input.InputManager

/**
 * Выводит информацию о коллекции (тип, дата инициализации, размер).
 * @property manager менеджер коллекции
 */
class InfoCommand(
    private val manager: CollectionManager,
    private val console: InputManager
) : Command {
    override val name = "info"
    override val description = "вывести информацию о коллекции"

    override fun execute(args: List<String>) = console.print(manager.getInfo())
}
