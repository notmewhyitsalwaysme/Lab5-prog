import commands.Command
import runner.CommandInvoker
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CommandInvokerHistoryTest {

    /**
     * Тест 8: история хранит не более 12 последних команд.
     */
    @Test
    fun `history should not exceed 12 entries`() {
        val invoker = CommandInvoker()

        // Регистрируем 15 команд с именами cmd1..cmd15
        repeat(15) { i ->
            val cmd = mockk<Command>()
            every { cmd.name } returns "cmd${i + 1}"
            every { cmd.description } returns ""
            every { cmd.execute(any()) } returns Unit
            invoker.register(cmd)
        }

        repeat(15) { i -> invoker.execute("cmd${i + 1}") }

        val history = invoker.getHistory()
        assertEquals(12, history.size)
        // Должны остаться последние 12: cmd4..cmd15
        assertEquals("cmd4", history.first())
        assertEquals("cmd15", history.last())
    }
}