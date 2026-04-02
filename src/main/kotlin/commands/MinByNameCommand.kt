package commands

import collection.CollectionManager
import input.IOManager

/**
 * Выводит любой элемент с минимальным значением поля [models.HumanBeing.name].
 */
class MinByNameCommand(
    private val manager: CollectionManager,
    private val inputManager: IOManager
) : Command {
    override val name = "min_by_name"
    override val description = "вывести элемент с минимальным именем"

    override fun execute(args: List<String>) {
        val min = manager.minByName()
        if (min == null) inputManager.print("Коллекция пуста.")
        else inputManager.print(min.toString())
    }
}
