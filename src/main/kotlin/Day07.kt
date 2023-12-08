class CamelCards(input: String = "") {

    private val labels = "23456789TJQKA"
    private val hands: MutableList<CardHand> = mutableListOf()

    private val highHandMatch = Regex("(?:(.)(?!.*\\1))*")
    private val singlePairHand = Regex(".*(.)\\1+")
    private val twoPairHand = Regex(".*(.)\\1{1}.*(.)\\2{1}")
    private val threeOfAKindMatch = Regex(".*(.)\\1{2}")
    private val fullHouseHand = Regex("(.)\\1{1}(.)\\2{2}|(.)\\3{2}(.)\\4{1}")
    private val fourOfAKindHand = Regex(".*(.)\\1{3}")
    private val fiveOfAKindHand = Regex(".*(.)\\1{4}")

    fun getCardRank(card: String): Int {
        return labels.indexOf(card) + 1
    }

    fun getHandRank(cards: String): Int {
        val sortedCards = cards.toList().sorted().joinToString("")

        return when {
            fiveOfAKindHand.containsMatchIn(sortedCards) -> 7
            fourOfAKindHand.containsMatchIn(sortedCards) -> 6
            fullHouseHand.containsMatchIn(sortedCards) -> 5
            threeOfAKindMatch.containsMatchIn(sortedCards) -> 4
            twoPairHand.containsMatchIn(sortedCards) -> 3
            singlePairHand.containsMatchIn(sortedCards) -> 2
            highHandMatch.containsMatchIn(cards) -> 1
            else -> 0
        }
    }

    fun getRankedOrder(): List<CardHand> {
        val sortByRank = object : Comparator<CardHand> {
            override fun compare(hand1: CardHand?, hand2: CardHand?): Int {
                if (hand1 != null && hand2 != null) {
                    val hand1Rank = getHandRank(hand1.hand)
                    val hand2Rank = getHandRank(hand2.hand)
                    if (hand1Rank == hand2Rank) {
                        var index = 0
                        while (getCardRank(hand1.hand[index].toString()) == getCardRank(hand2.hand[index].toString())) {
                            index++
                        }
                        return getCardRank(hand1.hand[index].toString()) - getCardRank(hand2.hand[index].toString())
                    }
                    return hand1Rank - hand2Rank
                }
                return -1
            }
        }
        return hands.sortedWith(sortByRank)
    }

    fun getTotalWinnings(): Long {
        return getRankedOrder()
            .mapIndexed { index, cardHand ->
//                println("${cardHand.hand}: ${cardHand.bid.toLong()} * ${index + 1} = ${cardHand.bid.toLong() * (index + 1)}")
                (index + 1) * cardHand.bid.toLong()
            }
            .sum()
    }

    private fun parseHands(input: String) {
        if (input.isNotEmpty()) {
            input.split("\n")
                .forEach {
                    val parts = it.split(" ")
                    hands.add(CardHand(parts[0], parts[1].toInt()))
                }
        }
    }

    init {
        parseHands(input)
    }

}

data class CardHand(
    val hand: String,
    val bid: Int
)