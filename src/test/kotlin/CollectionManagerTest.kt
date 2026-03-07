import collection.CollectionManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

class CollectionManagerTest {

    private lateinit var manager: CollectionManager

    @BeforeEach
    fun setUp() {
        manager = CollectionManager()
    }

    /**
     * Тест 3: add добавляет элемент, size увеличивается.
     */
    @Test
    fun `add should increase collection size`() {
        val human = TestData.human("Bob")
        assertTrue(manager.add(human))
        assertEquals(1, manager.size())
    }

    /**
     * Тест 4: removeById удаляет существующий элемент.
     */
    @Test
    fun `removeById should remove existing element`() {
        val human = TestData.human("Carol")
        manager.add(human)
        assertTrue(manager.removeById(human.id))
        assertEquals(0, manager.size())
        assertNull(manager.getById(human.id))
    }
}
