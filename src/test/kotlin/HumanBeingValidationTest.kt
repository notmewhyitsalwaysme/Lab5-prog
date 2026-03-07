import models.Car
import models.Coordinates
import models.HumanBeing
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class HumanBeingValidationTest {

    /**
     * Тест 1: HumanBeing с пустым именем должен бросать исключение.
     */
    @Test
    fun `blank name should throw IllegalArgumentException`() {
        assertThrows<IllegalArgumentException> {
            HumanBeing(
                name = "   ", coordinates = Coordinates(0L, 0),
                realHero = false, hasToothpick = false,
                impactSpeed = 1.0, soundtrackName = "track",
                minutesOfWaiting = 1f, weaponType = null,
                car = Car(false)
            )
        }
    }

    /**
     * Тест 2: weaponType может быть null.
     */
    @Test
    fun `weaponType can be null`() {
        val human = TestData.human(weaponType = null)
        assertEquals(null, human.weaponType)
    }
}
