import models.Car
import models.Coordinates
import models.HumanBeing
import models.WeaponType

object TestData {
    fun human(
        name: String = "Alice",
        minutesOfWaiting: Float = 30f,
        weaponType: WeaponType? = WeaponType.HAMMER
    ) = HumanBeing(
        name = name, coordinates = Coordinates(10L, 20),
        realHero = true, hasToothpick = false,
        impactSpeed = 9.5, soundtrackName = "Test Track",
        minutesOfWaiting = minutesOfWaiting, weaponType = weaponType,
        car = Car(true)
    )
}
