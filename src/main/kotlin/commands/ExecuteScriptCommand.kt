package commands

import input.IOManager

/**
 * Считывает скрипт из файла в [IOManager], затем выполняет все команды.
 *
 * Фаза 1 (чтение): рекурсивно обходит все вложенные execute_script,
 * собирает команды в очередь.
 * Фаза 2 (выполнение): последовательно выполняет команды из очереди.
 *
 * Такой подход гарантирует, что все файлы прочитаны до начала выполнения.
 */
class ExecuteScriptCommand(
    private val inputManager: IOManager
) : Command {
    override val name = "execute_script"
    override val description = "выполнить скрипт из файла: execute_script <file>"

    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            inputManager.printErrConsole("[Ошибка] Укажите путь к файлу. Пример: execute_script script.txt")
            return
        }

        val filePath = args[0]

        inputManager.print("Чтение скрипта '$filePath'...")
        inputManager.addScriptScanner(filePath)
    }
}
