package commands

import input.ScriptInputManager
import runner.CommandInvoker

/**
 * Читает и выполняет команды из файла-скрипта.
 * Синтаксис: execute_script <file_path>
 *
 * Защита от рекурсии: отслеживает стек вызванных скриптов.
 */
class ExecuteScriptCommand(
    private val invoker: CommandInvoker,
    private val activeScripts: MutableSet<String> = mutableSetOf()
) : Command {
    override val name = "execute_script"
    override val description = "выполнить скрипт из файла: execute_script <file>"

    override fun execute(args: List<String>) {
        if (args.isEmpty()) {
            println("[Ошибка] Укажите путь к файлу. Пример: execute_script script.txt")
            return
        }
        val filePath = args[0]

        if (filePath in activeScripts) {
            println("[Ошибка] Обнаружена рекурсия: скрипт '$filePath' уже выполняется.")
            return
        }

        val scriptInput = try {
            ScriptInputManager(filePath)
        } catch (e: Exception) {
            println("[Ошибка] Не удалось открыть файл '$filePath': ${e.message}")
            return
        }

        activeScripts.add(filePath)
        println("Выполнение скрипта '$filePath'...")

        try {
            var line = scriptInput.readLine()
            while (line != null) {
                if (line.isNotBlank()) {
                    println("  [скрипт] > $line")
                    invoker.execute(line)
                }
                line = scriptInput.readLine()
            }
        } catch (e: IllegalStateException) {
            println("[Ошибка] ${e.message}")
        } finally {
            activeScripts.remove(filePath)
            println("Скрипт '$filePath' завершён.")
        }
    }
}
