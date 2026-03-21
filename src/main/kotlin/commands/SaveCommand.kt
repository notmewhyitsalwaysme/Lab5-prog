package commands

import collection.CollectionManager
import file.FileManager

/**
 * Сохраняет коллекцию в CSV-файл.
 * @property manager менеджер коллекции
 * @property fileManager менеджер файла
 */
class SaveCommand(
    private val manager: CollectionManager,
    private val fileManager: FileManager,
) : Command {
    override val name = "save"
    override val description = "сохранить коллекцию в файл"

    override fun execute(args: List<String>) {
        fileManager.write(manager.getAll())
    }
}
