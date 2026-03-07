package commands

import collection.CollectionManager

/**
 * Выводит значения поля [models.HumanBeing.minutesOfWaiting] в порядке убывания.
 */
class PrintDescendingMinutesCommand(private val manager: CollectionManager) : Command {
    override val name = "print_field_descending_minutes_of_waiting"
    override val description = "вывести minutesOfWaiting в порядке убывания"

    override fun execute(args: List<String>) {
        val values = manager.getMinutesOfWaitingDescending()
        if (values.isEmpty()) println("Коллекция пуста.")
        else values.forEach { println(it) }
    }
}
