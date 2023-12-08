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
        val cards = CamelCards("32T3K 765\n" +
                "T55J5 684\n" +
                "KK677 28\n" +
                "KTJJT 220\n" +
                "QQQJA 483")
        val result = cards.getTotalWinnings()
        assertEquals(6440, result)
    }

}