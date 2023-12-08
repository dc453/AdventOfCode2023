import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals

class Day07Tests {

    @Test
    fun `should know rank of cards`() {
        val cards = CamelCards()
        assertAll(
            "cards",
            { assertEquals(1, cards.getCardRank("2"), "card 2") },
            { assertEquals(2, cards.getCardRank("3"), "card 3") },
            { assertEquals(3, cards.getCardRank("4"), "card 4") },
            { assertEquals(4, cards.getCardRank("5"), "card 5") },
            { assertEquals(5, cards.getCardRank("6"), "card 6") },
            { assertEquals(6, cards.getCardRank("7"), "card 7") },
            { assertEquals(7, cards.getCardRank("8"), "card 8") },
            { assertEquals(8, cards.getCardRank("9"), "card 9") },
            { assertEquals(9, cards.getCardRank("T"), "card T") },
            { assertEquals(10, cards.getCardRank("J"), "card J") },
            { assertEquals(11, cards.getCardRank("Q"), "card Q") },
            { assertEquals(12, cards.getCardRank("K"), "card K") },
            { assertEquals(13, cards.getCardRank("A"), "card A") },
        )
    }

    @Test
    fun `should recognize high card hand`() {
        val cards = CamelCards()
        val result = cards.getHandRank("23456")
        assertEquals(1, result)
    }

    @Test
    fun `should recognize a single pair hand`() {
        val cards = CamelCards()
        val result = cards.getHandRank("A23A4")
        assertEquals(2, result)
    }

    @Test
    fun `should recognize a two pair hand`() {
        val cards = CamelCards()
        val result = cards.getHandRank("23432")
        assertEquals(3, result)
    }

    @Test
    fun `should recognize three of a kind hand`() {
        val cards = CamelCards()
        val result = cards.getHandRank("TTT98")
        assertEquals(4, result)
    }

    @Test
    fun `should recognize full house hand`() {
        val cards = CamelCards()
        val result = cards.getHandRank("23332")
        assertEquals(5, result)
    }

    @Test
    fun `should recognize four of a kind hand`() {
        val cards = CamelCards()
        val result = cards.getHandRank("AA8AA")
        assertEquals(6, result)
    }

    @Test
    fun `should recognize five of a kind hand`() {
        val cards = CamelCards()
        val result = cards.getHandRank("AAAAA")
        assertEquals(7, result)
    }

    @Test
    fun `should rank hands`() {
        val cards = CamelCards("32T3K 765\n" +
                "T55J5 684\n" +
                "KK677 28\n" +
                "KTJJT 220\n" +
                "QQQJA 483")
        val expected = listOf(
            "32T3K",
            "KTJJT",
            "KK677",
            "T55J5",
            "QQQJA"
        )

        val result = cards.getRankedOrder()
            .map { it.hand }

        assertEquals(expected, result)
    }

    @Test
    fun `should calculate total winnings`() {
        val cards = CamelCards("2345A 1\n" +
                "Q2KJJ 13\n" +
                "Q2Q2Q 19\n" +
                "T3T3J 17\n" +
                "T3Q33 11\n" +
                "2345J 3\n" +
                "J345A 2\n" +
                "32T3K 5\n" +
                "T55J5 29\n" +
                "KK677 7\n" +
                "KTJJT 34\n" +
                "QQQJA 31\n" +
                "JJJJJ 37\n" +
                "JAAAA 43\n" +
                "AAAAJ 59\n" +
                "AAAAA 61\n" +
                "2AAAA 23\n" +
                "2JJJJ 53\n" +
                "JJJJ2 41")
        val result = cards.getTotalWinnings()
        assertEquals(6592, result)
    }

}