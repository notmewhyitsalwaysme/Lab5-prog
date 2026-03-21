package commands

import collection.CollectionManager
import input.HumanBeingBuilder
import input.InputManager

/**
 * Добавляет элемент в коллекцию, если он больше наибольшего элемента.
 * Сравнение по [models.HumanBeing.compareTo].
 */
class AddIfMaxCommand(
    private val manager: CollectionManager,
    private val inputManager: InputManager,
) : Command {
    override val name = "add_if_max"
    override val description = "добавить элемент, если он больше максимального"

    override fun execute(args: List<String>) {
        val human = HumanBeingBuilder(inputManager).build()
        val max   = manager.getMax()
        if (max == null || human > max) {
            manager.add(human)
            inputManager.print("Элемент добавлен (превышает максимум).")
        } else {
            inputManager.print("Элемент не добавлен: не превышает текущий максимум (${max.name}).")
        }
    }
}
