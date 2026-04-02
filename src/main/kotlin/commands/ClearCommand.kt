package commands

import collection.CollectionManager
import input.IOManager

/**
 * Очищает коллекцию.
 * @property manager менеджер коллекции
 */
class ClearCommand(
    private val manager: CollectionManager,
    private val inputManager: IOManager
) : Command {
    override val name = "clear"
    override val description = "очистить коллекцию"

    override fun execute(args: List<String>) {
        manager.clear()
        inputManager.print("Коллекция очищена.")
    }
}
