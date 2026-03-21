package commands

import collection.CollectionManager
import input.InputManager

/**
 * Выводит все элементы коллекции в строковом представлении.
 * @property manager менеджер коллекции
 */
class ShowCommand(
    private val manager: CollectionManager,
    private val console: InputManager
) : Command {
    override val name = "show"
    override val description = "вывести все элементы коллекции"

    override fun execute(args: List<String>) {
        if (manager.isEmpty()) {
            console.print("Коллекция пуста.")
            return
        }
        manager.getAll().forEach { console.print(it.toString()) }
    }
}
