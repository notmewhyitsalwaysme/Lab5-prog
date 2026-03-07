import collection.CollectionManager
import commands.*
import file.FileManager
import input.ConsoleInputManager
import input.InputManager
import runner.CommandInvoker

/**
 * Оооооооо, точка входа приложения.
 *
 * Ожидает один аргумент командной строки — путь к CSV-файлу коллекции.
 * При запуске загружает коллекцию из файла, затем запускает интерактивный REPL-цикл.
 *
 */
fun main(args: Array<String>) {
    // Проверяем аргументы
    if (args.isEmpty()) {
        System.err.println("Ошибка: укажите путь к файлу коллекции.")
        System.err.println("Использование: java -jar lab5.jar <file.csv>")
        return
    }

    val filePath = args[0]

    // Включаем Nightcall
    val fileManager = FileManager(filePath)
    val manager = CollectionManager()
    val inputManager : InputManager = ConsoleInputManager()
    val invoker = CommandInvoker()

    // Загружаем данные из файла
    val loaded = fileManager.read()
    manager.loadFromFile(loaded)

    // Регистрируем команды
    registerCommands(invoker, manager, fileManager, inputManager)

    // Водила, стартуем. Я сказала стартуем!
    println("Коллекция загружена. Введите 'help' для справки.")
    runRepl(inputManager, invoker)
}

/**
 * Запускает интерактивный режим.
 *
 * Читает строки из [inputManager] и передаёт их [invoker].
 * Завершается при EOF (Ctrl+D).
 *
 * @param inputManager источник ввода
 * @param invoker инвокер команд
 */
fun runRepl(inputManager: InputManager, invoker: CommandInvoker) {
    while (true) {
        if (inputManager.isInteractive) print("\n ☭ ") // genius idea™

        val line = inputManager.readLine()

        // EOF — завершаем без сохранения
        if (line == null) {
            println("\nНу ладно.")
            break
        }

        if (line.isBlank()) continue

        invoker.execute(line)
    }
}

/**
 * Регистрирует все команды приложения в [invoker].
 *
 * @param invoker инвокер команд
 * @param manager менеджер коллекции
 * @param fileManager менеджер файла
 * @param inputManager источник ввода
 */
fun registerCommands(
    invoker: CommandInvoker,
    manager: CollectionManager,
    fileManager: FileManager,
    inputManager: InputManager
) {
    val activeScripts = mutableSetOf<String>()

    listOf(
        HelpCommand(invoker),
        InfoCommand(manager),
        ShowCommand(manager),
        AddCommand(manager, inputManager),
        UpdateCommand(manager, inputManager),
        RemoveByIdCommand(manager),
        ClearCommand(manager),
        SaveCommand(manager, fileManager),
        ExecuteScriptCommand(invoker, activeScripts),
        ExitCommand(),
        AddIfMaxCommand(manager, inputManager),
        AddIfMinCommand(manager, inputManager),
        HistoryCommand(invoker),
        SumOfMinutesCommand(manager),
        MinByNameCommand(manager),
        PrintDescendingMinutesCommand(manager)
    ).forEach { invoker.register(it) }
}
