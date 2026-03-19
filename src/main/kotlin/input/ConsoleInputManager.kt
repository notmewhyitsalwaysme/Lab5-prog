package input

import org.jline.reader.EndOfFileException
import org.jline.reader.LineReaderBuilder
import org.jline.reader.UserInterruptException
import org.jline.reader.impl.completer.StringsCompleter
import org.jline.terminal.TerminalBuilder

/**
 * Реализация [InputManager] для чтения из стандартного потока ввода на основе JLine3.
 * Поддерживает историю команд (стрелки ↑↓), Tab-автодополнение,
 * корректную обработку Ctrl+C и Ctrl+D.
 */
class ConsoleInputManager() : InputManager {
    override val isInteractive = true

    private val terminal = TerminalBuilder.builder()
        .system(true)
        .build()

    private val reader = LineReaderBuilder.builder()
        .terminal(terminal)
        .variable("history-size", 100)
        .build()

    /**
     * Читает строку с промптом ☭.
     * Возвращает null при Ctrl+D (EOF) или Ctrl+C.
     */
    override fun readLine(): String? {
        return try {
            reader.readLine("\n ☭ ")
        } catch (e: EndOfFileException) {
            null   // Ctrl+D
        } catch (e: UserInterruptException) {
            null   // Ctrl+C
        }
    }
}


