package commands

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
class ExecuteScriptCommand(private val invoker: CommandInvoker) : Command {
    override val name = "execute_script"
    override val description = "выполнить скрипт из файла: execute_script <file>"

    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("[Ошибка] Укажите путь к файлу. Пример: execute_script script.txt")
            return
        }

        val filePath = args[0]

        // я манал это всё
        println("Чтение скрипта '$filePath'...")
        val queue = CommandQueue()
        queue.loadFromScript(filePath)
        println("Команды в очереди: ${queue.peek()}") // временно

        if (queue.isEmpty()) {
            println("Текст Скриптонита пустой или не найден.")
            return
        }

        println("Прочитано команд: ${queue.size()}. Начинаем выполнение...")

        while (!queue.isEmpty()) {
            val line = queue.dequeue() ?: break
            println("  [скриптонит] > $line")
            invoker.execute(line)
        }

        println("Скриптонит зачитал '$filePath'.")
    }
}
