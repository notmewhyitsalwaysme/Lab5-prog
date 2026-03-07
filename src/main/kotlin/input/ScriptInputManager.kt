package input

import java.io.File
import java.util.Scanner

/**
 * Реализация [InputManager] для чтения команд из файла.
 *
 * Используется командой [commands.ExecuteScriptCommand].
 * При достижении конца файла возвращает null.
 *
 * @property filePath путь к файлу
 */
class ScriptInputManager(filePath: String) : InputManager {
    override val isInteractive: Boolean = false

    private val scanner = Scanner(File(filePath), Charsets.UTF_8)

    override fun readLine(): String? =
        if (scanner.hasNextLine()) scanner.nextLine() else null
}
