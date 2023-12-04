import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day03Tests {

    @Test
    fun `should detect numbers`() {
        val engineSchematic = EngineSchematic(
            ".123*...\n" +
                "...456$."
        )
        val result = engineSchematic.getNumbers()
        assertEquals(listOf(123, 456), result)
    }

    @Test
    fun `should filter out numbers without adjacent symbols above number`() {
        var engineSchematic = EngineSchematic(
            ".....\n" +
                    ".123."
        )
        assertEquals(0, engineSchematic.getNumbers().size)

        engineSchematic = EngineSchematic(
            "*....\n" +
                    ".123."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

        engineSchematic = EngineSchematic(
            ".*...\n" +
                    ".123."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

        engineSchematic = EngineSchematic(
            "..*..\n" +
                    ".123."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

        engineSchematic = EngineSchematic(
            "...*.\n" +
                    ".123."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

        engineSchematic = EngineSchematic(
            "....*\n" +
                    ".123."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())
    }

    @Test
    fun `should filter out numbers without adjacent symbols on same line as number`() {
        var engineSchematic = EngineSchematic(
            ".123."
        )
        assertEquals(0, engineSchematic.getNumbers().size)

        engineSchematic = EngineSchematic(
                    "*123."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

        engineSchematic = EngineSchematic(
                    ".123*"
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

    }

    @Test
    fun `should filter out numbers without adjacent symbols below number`() {
        var engineSchematic = EngineSchematic(
            ".123.\n" +
                    "....."
        )
        assertEquals(0, engineSchematic.getNumbers().size)

        engineSchematic = EngineSchematic(
            ".123.\n" +
                    "*...."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

        engineSchematic = EngineSchematic(
            ".123.\n" +
                    ".*..."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

        engineSchematic = EngineSchematic(
            ".123.\n" +
                    "..*.."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

        engineSchematic = EngineSchematic(
            ".123.\n" +
                    "...*."
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())

        engineSchematic = EngineSchematic(
            ".123.\n" +
                    "....*"
        )
        assertEquals(listOf(123), engineSchematic.getNumbers())
    }

    @Test
    fun `should get correct index of duplicate numbers`() {
        val engineSchematic = EngineSchematic("123*..123")
        assertEquals(listOf(123), engineSchematic.getNumbers())
    }

    @Test
    fun `should calculate sum of numbers`() {
        val engineSchematic = EngineSchematic(
            "467..114..\n" +
                "...*......\n" +
                "..35..633.\n" +
                "......#...\n" +
                "617*......\n" +
                ".....+.58.\n" +
                "..592.....\n" +
                "......755.\n" +
                "...\$.*....\n" +
                ".664.598..")
        val result = engineSchematic.getTotal()
        assertEquals(4361, result)
    }

}