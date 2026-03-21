package commands

import collection.CollectionManager
import input.IOManager

/**
 * Выводит значения поля [models.HumanBeing.minutesOfWaiting] в порядке убывания.
 */
class PrintDescendingMinutesCommand(
    private val manager: CollectionManager,
    private val console: IOManager
) : Command {
    override val name = "print_field_descending_minutes_of_waiting"
    override val description = "вывести minutesOfWaiting в порядке убывания"

    override fun execute(args: List<String>) {
        val values = manager.getMinutesOfWaitingDescending()
        if (values.isEmpty()) console.print("Коллекция пуста.")
        else values.forEach { console.print(it.toString()) }
    }
}
