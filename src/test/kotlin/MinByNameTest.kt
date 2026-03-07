import collection.CollectionManager
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MinByNameTest {

    /**
     * Тест 9: minByName возвращает элемент с минимальным (который идёт выше по алфавиту) именем.
     */
    @Test
    fun `minByName should return element with smallest name`() {
        val manager = CollectionManager()
        manager.add(TestData.human("Zorro"))
        manager.add(TestData.human("Alice"))
        manager.add(TestData.human("Mike"))

        assertEquals("Alice", manager.minByName()?.name)
    }
}