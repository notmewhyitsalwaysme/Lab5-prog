import collection.CollectionManager
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PrintDescendingMinutesTest {

    /**
     * Тест 10: getMinutesOfWaitingDescending возвращает список в порядке убывания.
     */
    @Test
    fun `minutes should be sorted descending`() {
        val manager = CollectionManager()
        manager.add(TestData.human("A", minutesOfWaiting = 5f))
        manager.add(TestData.human("B", minutesOfWaiting = 15f))
        manager.add(TestData.human("C", minutesOfWaiting = 10f))

        val result = manager.getMinutesOfWaitingDescending()
        assertEquals(listOf(15f, 10f, 5f), result)
    }
}