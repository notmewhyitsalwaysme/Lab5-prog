package commands

import input.InputManager
import runner.CommandInvoker
import runner.CommandQueue

/**
 * Считывает скрипт из файла в [CommandQueue], затем выполняет все команды.
 *
 * Фаза 1 (чтение): рекурсивно обходит все вложенные execute_script,
 * собирает команды в очередь.
 * Фаза 2 (выполнение): последовательно выполняет команды из очереди.
 *
 * Такой подход гарантирует, что все файлы прочитаны до начала выполнения.
 */
class ExecuteScriptCommand(
    private val invoker: CommandInvoker,
    private val console: InputManager
) : Command {
    override val name = "execute_script"
    override val description = "выполнить скрипт из файла: execute_script <file>"

    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            console.print("[Ошибка] Укажите путь к файлу. Пример: execute_script script.txt")
            return
        }

        val filePath = args[0]

        // я манал это всё
        console.print("Чтение скрипта '$filePath'...")

        // inputmanager добавить источник, Scanner file
        val queue = CommandQueue()
        queue.loadFromScript(filePath)

        if (queue.isEmpty()) {
            console.print("Текст Скриптонита пустой или не найден.")
            return
        }

        console.print("Прочитано команд: ${queue.size()}. Начинаем выполнение...")

        // это уже есть
        while (!queue.isEmpty()) {
            val line = queue.dequeue() ?: break
            console.print("  [скриптонит] > $line")
            invoker.execute(line)
        }

        console.print("Скриптонит зачитал '$filePath'.")
    }
}
