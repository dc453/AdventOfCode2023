class CamelCards(input: String = "", val enableWildcardMode: Boolean = false) {

    private val labels = if (enableWildcardMode) "J23456789TQKA" else "23456789TJQKA"
    private val hands: MutableList<CardHand> = mutableListOf()

    private val highHandMatch = Regex("(?:(.)(?!.*\\1))*")
    private val singlePairHand = Regex(".*(.)\\1+")
    private val singlePairWildcard = Regex("J")
    private val twoPairHand = Regex(".*(.)\\1{1}.*(.)\\2{1}")
    private val threeOfAKind = Regex(".*(.)\\1{2}")
    private val threeOfAKindWildcard = Regex("([^J])\\1{1}[^J]*J{1}|J{1}[^J]*([^J])\\2{1}|J{2}")
    private val fullHouseHand = Regex("(.)\\1{1}(.)\\2{2}|(.)\\3{2}(.)\\4{1}")
    private val fullHouseWildcard = Regex("[^J]*([^J])\\1{1}[^J]*([^J])\\2{1}J|([^J])\\3{1}J([^J])\\4{1}[^J]*|J([^J])\\5{1}[^J]*([^J])\\6{1}J")
    private val fourOfAKindHand = Regex(".*(.)\\1{3}")
    private val fourOfAKindWildcard = Regex("([^J])\\1{2}[^J]*J+|J+([^J])\\2{2}|([^J])\\3{1}[^J]*J{2}|J{2}[^J]*([^J])\\4{1}|J{3}")
    private val fiveOfAKindHand = Regex(".*(.)\\1{4}")
    private val fiveOfAKindWildcard = Regex("(.)\\1{3}J|J(.)\\2{3}|(.)\\3{2}J{2}|J{2}(.)\\4{2}|(.)\\5{1}J{3}|J{3}(.)\\6{1}|J{4,5}")

    fun getCardRank(card: String): Int {
        return labels.indexOf(card) + 1
    }

    fun getHandRank(cards: String): Int {
        val sortedCards = cards.toList().sorted().joinToString("")

        if (fiveOfAKindHand.containsMatchIn(sortedCards) || (enableWildcardMode && fiveOfAKindWildcard.containsMatchIn(sortedCards))) {
            return 7
        }
        else if (fourOfAKindHand.containsMatchIn(sortedCards) || (enableWildcardMode && fourOfAKindWildcard.containsMatchIn(sortedCards))) {
            return 6
        }
        else if (fullHouseHand.containsMatchIn(sortedCards) || (enableWildcardMode && fullHouseWildcard.containsMatchIn(sortedCards))) {
            return 5
        }
        else if (threeOfAKind.containsMatchIn(sortedCards) || (enableWildcardMode && threeOfAKindWildcard.containsMatchIn(sortedCards))) {
            return 4
        }
        else if (twoPairHand.containsMatchIn(sortedCards)) { // Will always be 3 of a kind with wildcard
            return 3
        }
        else if (singlePairHand.containsMatchIn(sortedCards) || (enableWildcardMode && singlePairWildcard.containsMatchIn(sortedCards))) {
            return 2
        }
        else if (highHandMatch.containsMatchIn(sortedCards)) {
            return 1
        }
        return 0
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
//                println("${cardHand.hand}: ${getHandRank(cardHand.hand)} -> ${cardHand.bid.toLong()} * ${index + 1} = ${cardHand.bid.toLong() * (index + 1)}")
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