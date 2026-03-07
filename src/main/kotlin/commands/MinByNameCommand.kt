package commands

import collection.CollectionManager

/**
 * Выводит любой элемент с минимальным значением поля [models.HumanBeing.name].
 */
class MinByNameCommand(private val manager: CollectionManager) : Command {
    override val name = "min_by_name"
    override val description = "вывести элемент с минимальным именем"

    override fun execute(args: List<String>) {
        val min = manager.minByName()
        if (min == null) println("Коллекция пуста.")
        else println(min)
    }
}
