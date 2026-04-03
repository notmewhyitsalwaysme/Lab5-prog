import collection.CollectionManager
import commands.AddIfMaxCommand
import commands.AddIfMinCommand
import input.HumanBeingBuilder
import input.IOManager
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AddIfCommandsTest {

    private lateinit var manager: CollectionManager
    private val inputManager = mockk<IOManager>(relaxed = true)

    @BeforeEach
    fun setUp() {
        manager = CollectionManager()
    }

    @AfterEach
    fun tearDown() {
        unmockkConstructor(HumanBeingBuilder::class)
    }

    /**
     * Тест 5: add_if_max добавляет элемент, если он больше максимального.
     */
    @Test
    fun `add_if_max should add element greater than current max`() {
        manager.add(TestData.human("Alice"))   // min элемент
        val bigHuman = TestData.human("Zorro") // 'Z' > 'A' по compareTo

        // Мокируем HumanBeingBuilder через InputManager
        mockkConstructor(HumanBeingBuilder::class)
        every { anyConstructed<HumanBeingBuilder>().build() } returns bigHuman

        AddIfMaxCommand(manager, inputManager).execute(emptyList())

        assertEquals(2, manager.size())
    }

    /**
     * Тест 6: add_if_min НЕ добавляет элемент, если он больше минимального.
     */
    @Test
    fun `add_if_min should NOT add element greater than current min`() {
        manager.add(TestData.human("Alice"))
        val bigHuman = TestData.human("Zorro")

        mockkConstructor(HumanBeingBuilder::class)
        every { anyConstructed<HumanBeingBuilder>().build() } returns bigHuman

        AddIfMinCommand(manager, inputManager).execute(emptyList())

        assertEquals(1, manager.size()) // не добавлен
    }
}
