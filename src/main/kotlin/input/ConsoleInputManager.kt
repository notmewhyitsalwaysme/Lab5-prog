package input

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Реализация [InputManager] для чтения из стандартного потока ввода.
 * Использует [BufferedReader] с кодировкой системы по умолчанию,
 * что обеспечивает корректную работу на Windows и Linux.
 * Без этого - сломается. Не трогать!
 */
class ConsoleInputManager : InputManager {
    override val isInteractive: Boolean = true

    private val reader = BufferedReader(
        InputStreamReader(System.`in`, Charsets.UTF_8)
    )

    override fun readLine(): String? = reader.readLine()
}

