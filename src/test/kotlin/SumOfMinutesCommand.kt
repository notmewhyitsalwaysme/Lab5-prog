import collection.CollectionManager
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SumOfMinutesTest {

    /**
     * Тест 7: сумма minutesOfWaiting считается корректно.
     */
    @Test
    fun `sumOfMinutesOfWaiting returns correct sum`() {
        val manager = CollectionManager()
        manager.add(TestData.human("Alice", minutesOfWaiting = 10f))
        manager.add(TestData.human("Bob",   minutesOfWaiting = 20f))
        manager.add(TestData.human("Carol", minutesOfWaiting = 5f))

        assertEquals(35.0, manager.sumOfMinutesOfWaiting(), absoluteTolerance = 0.001)
    }
}