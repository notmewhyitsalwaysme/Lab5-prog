package runner

import java.io.File
import java.util.Scanner
import input.IOManager

/**
 * Очередь команд, собранных из файла-скрипта.
 *
 * Фаза 1: [loadFromScript] — рекурсивно читает файл(ы) и заполняет очередь.
 * Фаза 2: [dequeue] — последовательно отдаёт команды на выполнение.
 *
 * Вложенные вызовы [EXECUTE_SCRIPT_CMD] раскрываются сразу при чтении —
 * команды вставляются в очередь в порядке встречи.
 */
class CommandQueue {

    companion object {
        private const val EXECUTE_SCRIPT_CMD = "execute_script"
    }

    private val console = IOManager()
    private val queue = ArrayDeque<String>()

    /** Количество команд в очереди. */
    fun size(): Int = queue.size

    /** Возвращает true если очередь пуста. */
    fun isEmpty(): Boolean = queue.isEmpty()

    /**
     * Рекурсивно читает скрипт и все вложенные скрипты,
     * заполняя очередь командами в порядке встречи.
     *
     * Защита от бесконечной рекурсии — через [visitedScripts].
     *
     * @param filePath путь к файлу-скрипту
     * @param visitedScripts стек уже посещённых файлов (для защиты от рекурсии)
     */
    fun loadFromScript(
        filePath: String,
        visitedScripts: MutableSet<String> = mutableSetOf()
    ) {
        // Защита от рекурсии
        if (filePath in visitedScripts) {
            console.print("[CommandQueue] Рекурсия обнаружена: '$filePath' уже в стеке, пропускаем.")
            return
        }

        val file = File(filePath)
        if (!file.exists()) {
            console.print("[CommandQueue] Файл не найден: '$filePath'")
            return
        }
        if (!file.canRead()) {
            console.print("[CommandQueue] Нет прав на чтение: '$filePath'")
            return
        }

        // а вдруг вложил
        val parentDir = file.parentFile ?: File(".")

        visitedScripts.add(filePath)

        // Лениво читаю файл, пока Сканер не вернет null, т.е. файл закончится
        Scanner(file, Charsets.UTF_8).use { scanner ->
            generateSequence { if (scanner.hasNextLine()) scanner.nextLine() else null }
                .map { it.trim() }
                .filter { it.isNotBlank() }
                .forEach { line ->
                    val parts = line.split("\\s+".toRegex())
                    if (parts[0].lowercase() == EXECUTE_SCRIPT_CMD && parts.size > 1) {
                        val nestedPath = File(parentDir, parts[1]).path
                        if (!File(nestedPath).exists()) {
                            console.print("[CommandQueue] Файл не найден: '$nestedPath'")
                            return
                        }
                        loadFromScript(nestedPath, visitedScripts)
                    } else {
                        queue.addLast(line)
                    }
                }
        }

        visitedScripts.remove(filePath)
    }

    /**
     * Извлекает следующую команду из начала очереди.
     * @return строка команды или null если очередь пуста
     */
    fun dequeue(): String? = queue.removeFirstOrNull()

    /**
     * Возвращает все команды в очереди (без удаления).
     * Полезно для отладки.
     */
    fun peek(): List<String> = queue.toList()
}
