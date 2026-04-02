package commands

import collection.CollectionManager
import input.HumanBeingBuilder
import input.IOManager
import java.util.UUID

/**
 * Обновляет элемент коллекции по его id.
 * Синтаксис: update <uuid>
 * Новые поля вводятся интерактивно через [HumanBeingBuilder].
 */
class UpdateCommand(
    private val manager: CollectionManager,
    private val inputManager: IOManager,
) : Command {
    override val name = "update"
    override val description = "обновить элемент по id: update <uuid>"

    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            inputManager.print("[Ошибка] Укажите id элемента. Пример: update <uuid>")
            return
        }
        val id = try {
            UUID.fromString(args[0])
        } catch (e: IllegalArgumentException) {
            inputManager.print("[Ошибка] Некорректный UUID: '${args[0]}'")
            return
        }
        if (manager.getById(id) == null) {
            inputManager.print("[Ошибка] Элемент с id '$id' не найден.")
            return
        }
        val updated = HumanBeingBuilder(inputManager).build()
        manager.update(id, updated)
        inputManager.print("Элемент обновлён.")
    }
}
