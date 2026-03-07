package input

/**
 * Интерфейс источника ввода.
 *
 * Подменяет консольный ввод на чтение из файла
 * при выполнении команды [commands.ExecuteScriptCommand].
 */
interface InputManager {
    /**
     * Читает следующую строку из источника.
     * @return строка или null при достижении конца потока (EOF)
     */
    fun readLine(): String?

    /**
     * Возвращает true если источник — интерактивная консоль.
     * Используется для управления выводом приглашений (prompt).
     */
    val isInteractive: Boolean
}
