package commands

import collection.CollectionManager
import input.HumanBeingBuilder
import input.InputManager
import java.util.UUID

/**
 * Обновляет элемент коллекции по его id.
 * Синтаксис: update <uuid>
 * Новые поля вводятся интерактивно через [HumanBeingBuilder].
 */
class UpdateCommand(
    private val manager: CollectionManager,
    private val inputManager: InputManager
) : Command {
    override val name = "update"
    override val description = "обновить элемент по id: update <uuid>"

    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("[Ошибка] Укажите id элемента. Пример: update <uuid>")
            return
        }
        val id = try {
            UUID.fromString(args[0])
        } catch (e: IllegalArgumentException) {
            println("[Ошибка] Некорректный UUID: '${args[0]}'")
            return
        }
        if (manager.getById(id) == null) {
            println("[Ошибка] Элемент с id '$id' не найден.")
            return
        }
        val updated = HumanBeingBuilder(inputManager).build()
        manager.update(id, updated)
        println("Элемент обновлён.")
    }
}
