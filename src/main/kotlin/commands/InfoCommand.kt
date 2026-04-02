package commands

import collection.CollectionManager
import input.IOManager

/**
 * Выводит информацию о коллекции (тип, дата инициализации, размер).
 * @property manager менеджер коллекции
 */
class InfoCommand(
    private val manager: CollectionManager,
    private val inputManager: IOManager
) : Command {
    override val name = "info"
    override val description = "вывести информацию о коллекции"

    override fun execute(args: List<String>) = inputManager.print(manager.getInfo())
}
