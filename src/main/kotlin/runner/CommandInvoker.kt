package runner

import commands.Command
import input.IOManager

/**
 * Инвокер команд.
 *
 * Хранит реестр команд и историю последних 12 вызовов.
 * Разбирает введённую строку на имя команды и аргументы.
 *
 * @property commands зарегистрированные команды: имя → реализация
 */
class CommandInvoker {

    private val console = IOManager()
    private val commands = mutableMapOf<String, Command>()
    private val history  = ArrayDeque<String>(12)

    /**
     * Регистрирует команду в реестре.
     * @param command команда для регистрации
     */
    fun register(command: Command) {
        commands[command.name] = command
    }

    /**
     * Разбирает строку и выполняет соответствующую команду.
     * Добавляет имя команды в историю.
     *
     * @param input строка ввода от пользователя
     */
    fun execute(input: String) {
        val parts = input.trim().split(" ")
        val name = parts[0].lowercase().trim()
        val args = parts.drop(1)

        val command = commands[name]
        if (command == null) {
            console.print("Неизвестная команда: '$name'. Введите 'help' для справки.")
            return
        }

        addToHistory(name)
        try {
            command.execute(args)
        } catch (e: IllegalStateException) {
            console.print("[Ошибка выполнения] ${e.message}")
        }
    }

    /**
     * Возвращает неизменяемый список последних 12 команд.
     */
    fun getHistory(): List<String> = history.toList()

    /**
     * Возвращает все зарегистрированные команды для вывода справки.
     */
    fun getCommands(): Map<String, Command> = commands.toMap()

    private fun addToHistory(name: String) {
        if (history.size >= 12) history.removeFirst()
        history.addLast(name)
    }

    fun getCommandNames(): List<String> = commands.keys.toList()
}