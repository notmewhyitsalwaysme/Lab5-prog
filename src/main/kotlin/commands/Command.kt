package commands

/**
 * Базовый интерфейс для всех команд приложения.
 */
interface Command {

    // добавить перменную для


    /** Имя команды. */
    val name: String

    /** Краткое описание для команды [HelpCommand]. */
    val description: String

    /**
     * Выполняет команду.
     * @param args аргументы, переданные в той же строке, что и имя команды
     */
    fun execute(args: List<String>)
}
