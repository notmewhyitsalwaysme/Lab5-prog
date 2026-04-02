package commands

import collection.CollectionManager
import input.IOManager

/**
 * Выводит сумму значений поля [models.HumanBeing.minutesOfWaiting].
 */
class SumOfMinutesCommand(
    private val manager: CollectionManager,
    private val inputManager: IOManager
) : Command {
    override val name = "sum_of_minutes_of_waiting"
    override val description = "вывести сумму minutesOfWaiting всех элементов"

    override fun execute(args: List<String>) =
        inputManager.print("Сумма minutesOfWaiting: ${manager.sumOfMinutesOfWaiting()}")
}
