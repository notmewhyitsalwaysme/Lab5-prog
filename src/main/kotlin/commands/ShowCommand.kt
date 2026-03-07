package commands

import collection.CollectionManager

/**
 * Выводит все элементы коллекции в строковом представлении.
 * @property manager менеджер коллекции
 */
class ShowCommand(private val manager: CollectionManager) : Command {
    override val name = "show"
    override val description = "вывести все элементы коллекции"

    override fun execute(args: List<String>) {
        if (manager.isEmpty()) {
            println("Коллекция пуста.")
            return
        }
        manager.getAll().forEach { println(it) }
    }
}
