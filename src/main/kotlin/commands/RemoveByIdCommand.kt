package commands

import collection.CollectionManager
import input.IOManager
import java.util.UUID

/**
 * Удаляет элемент из коллекции по его id.
 * Синтаксис: remove_by_id <uuid>
 */
class RemoveByIdCommand(
    private val manager: CollectionManager,
    private val console: IOManager
) : Command {
    override val name = "remove_by_id"
    override val description = "удалить элемент по id: remove_by_id <uuid>"

    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            console.print("[Ошибка] Укажите id. Пример: remove_by_id <uuid>")
            return
        }
        val id = try {
            UUID.fromString(args[0])
        } catch (e: IllegalArgumentException) {
            console.print("[Ошибка] Некорректный UUID: '${args[0]}'")
            return
        }
        if (manager.removeById(id)) console.print("Элемент удалён.")
        else console.print("[Ошибка] Элемент с id '$id' не найден.")
    }
}
