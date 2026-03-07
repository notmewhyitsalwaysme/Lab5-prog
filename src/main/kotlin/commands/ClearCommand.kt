package commands

import collection.CollectionManager

/**
 * Очищает коллекцию.
 * @property manager менеджер коллекции
 */
class ClearCommand(private val manager: CollectionManager) : Command {
    override val name = "clear"
    override val description = "очистить коллекцию"

    override fun execute(args: List<String>) {
        manager.clear()
        println("Коллекция очищена.")
    }
}
