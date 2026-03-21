package commands

import collection.CollectionManager
import input.HumanBeingBuilder
import input.InputManager

/**
 * Добавляет элемент в коллекцию, если он меньше наименьшего элемента.
 * Сравнение по [models.HumanBeing.compareTo].
 */
class AddIfMinCommand(
    private val manager: CollectionManager,
    private val inputManager: InputManager
) : Command {
    override val name = "add_if_min"
    override val description = "добавить элемент, если он меньше минимального"

    override fun execute(args: List<String>) {
        val human = HumanBeingBuilder(inputManager).build()
        val min   = manager.getMin()
        if (min == null || human < min) {
            manager.add(human)
            inputManager.print("Элемент добавлен (меньше минимума).")
        } else {
            inputManager.print("Элемент не добавлен: не меньше текущего минимума (${min.name}).")
        }
    }
}
