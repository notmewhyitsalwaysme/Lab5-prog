package commands

import collection.CollectionManager

/**
 * Выводит информацию о коллекции (тип, дата инициализации, размер).
 * @property manager менеджер коллекции
 */
class InfoCommand(private val manager: CollectionManager) : Command {
    override val name = "info"
    override val description = "вывести информацию о коллекции"

    override fun execute(args: List<String>) = println(manager.getInfo())
}
