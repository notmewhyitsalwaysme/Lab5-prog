package commands

import collection.CollectionManager
import input.IOManager

/**
 * Выводит все элементы коллекции в строковом представлении.
 * @property manager менеджер коллекции
 */
class ShowCommand(
    private val manager: CollectionManager,
    private val inputManager: IOManager
) : Command {
    override val name = "show"
    override val description = "вывести все элементы коллекции"

    override fun execute(args: List<String>) {
        if (manager.isEmpty()) {
            inputManager.print("Коллекция пуста.")
            return
        }
        manager.getAll().forEach { inputManager.print(it.toString()) }
    }
}
