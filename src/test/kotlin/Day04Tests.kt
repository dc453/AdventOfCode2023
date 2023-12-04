import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day04Tests {

    @Test
    fun `should get winning numbers of card`() {
        val card = ScratchCard("")
        val result = card.getWinningNumbers("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        assertEquals(listOf(41, 48, 83, 86, 17), result)
    }

    @Test
    fun `should get drawn numbers of card`() {
        val card = ScratchCard("")
        val result = card.getDrawnNumbers("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        assertEquals(listOf(83, 86, 6, 31, 17, 9, 48, 53), result)
    }

    @Test
    fun `should calculate score of winning numbers drawn`() {
        val card = ScratchCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")
        val result = card.getScore()
        assertEquals(8, result)
    }

    @Test
    fun `should calculate score of 1 when only 1 winning number drawn`() {
        val card = ScratchCard("Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83")
        val result = card.getScore()
        assertEquals(1, result)
    }

    @Test
    fun `should calculate total score of all cards`() {
        val card = ScratchCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
                "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
                "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
                "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
                "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
                "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11")
        val result = card.getScore()
        assertEquals(13, result)
    }

}