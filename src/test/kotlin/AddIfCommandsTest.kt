import collection.CollectionManager
import commands.AddIfMaxCommand
import commands.AddIfMinCommand
import input.InputManager
import input.HumanBeingBuilder
import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AddIfCommandsTest {

    private lateinit var manager: CollectionManager
    private lateinit var inputManager: InputManager

    @BeforeEach
    fun setUp() {
        manager = CollectionManager()
        inputManager = mockk()
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
        unmockkConstructor(HumanBeingBuilder::class)
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
        unmockkConstructor(HumanBeingBuilder::class)
    }
}
