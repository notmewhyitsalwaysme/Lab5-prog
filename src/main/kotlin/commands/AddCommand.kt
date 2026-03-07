package commands

import collection.CollectionManager
import input.HumanBeingBuilder
import input.InputManager

/**
 * Добавляет новый элемент в коллекцию.
 * Поля вводятся интерактивно через [HumanBeingBuilder].
 */
class AddCommand(
    private val manager: CollectionManager,
    private val inputManager: InputManager
) : Command {
    override val name = "add"
    override val description = "добавить новый элемент в коллекцию"

    override fun execute(args: List<String>) {
        val human = HumanBeingBuilder(inputManager).build()
        if (manager.add(human)) println("Элемент добавлен: ${human.name} [${human.id}]")
        else println("Элемент уже существует в коллекции.")
    }
}
