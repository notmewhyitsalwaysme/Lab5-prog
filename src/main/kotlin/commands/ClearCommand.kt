package commands

import collection.CollectionManager
import input.InputManager

/**
 * Очищает коллекцию.
 * @property manager менеджер коллекции
 */
class ClearCommand(
    private val manager: CollectionManager,
    private val console: InputManager
) : Command {
    override val name = "clear"
    override val description = "очистить коллекцию"

    override fun execute(args: List<String>) {
        manager.clear()
        console.print("Коллекция очищена.")
    }
}
