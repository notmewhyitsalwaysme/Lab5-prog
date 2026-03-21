import collection.CollectionManager
import commands.*
import file.FileManager
import input.IOManager
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
    var filePath = "data.csv"

    if (args.isNotEmpty()) {
        filePath = args[0]
    }

    // Включаем Nightcall
    val fileManager = FileManager(filePath)
    val manager = CollectionManager()
    val invoker = CommandInvoker()
    val inputManager = IOManager()

    // Загружаем данные из файла
    val loaded = fileManager.read()
    manager.loadFromFile(loaded)

    // Регистрируем команды
    registerCommands(invoker, manager, fileManager, inputManager)

    // Водила, стартуем. Я сказала стартуем!
    inputManager.print("Коллекция загружена. Введите 'help' для справки.")
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
fun runRepl(
    inputManager: InputManager,
    invoker: CommandInvoker
    ) {

    while (true) {
        val line = inputManager.readLine() ?: break
        if (line.isBlank()) continue
        if (line == "exit") break

        // if command.isTerminating -> break
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
    inputManager: IOManager,

    ) {
    val activeScripts = mutableSetOf<String>()

    listOf(
        HelpCommand(invoker, inputManager),
        InfoCommand(manager, inputManager),
        ShowCommand(manager, inputManager),
        AddCommand(manager, inputManager),
        UpdateCommand(manager, inputManager),
        RemoveByIdCommand(manager, inputManager),
        ClearCommand(manager, inputManager),
        SaveCommand(manager, fileManager),
        ExecuteScriptCommand(invoker, inputManager),
        AddIfMaxCommand(manager, inputManager),
        AddIfMinCommand(manager, inputManager),
        HistoryCommand(invoker, inputManager),
        SumOfMinutesCommand(manager, inputManager),
        MinByNameCommand(manager, inputManager),
        PrintDescendingMinutesCommand(manager,inputManager),
        ExitCommand(inputManager)
    ).forEach { invoker.register(it) }
}
