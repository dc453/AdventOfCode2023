import kotlin.math.pow

class ScratchCard(private val cards: String) {

    private val winningNumbers: MutableList<Int> = mutableListOf()
    private var totalNumberOfCards = 0

    fun getWinningNumbers(card: String): List<Int> {
        val numbers = card.split(": ", " | ")
        return numbers[1]
            .trim()
            .split(Regex("\\s+"))
            .map { it.toInt() }
    }

    fun getDrawnNumbers(card: String): List<Int> {
        val numbers = card.split(": ", " | ")
        return numbers[2]
            .trim()
            .split(Regex("\\s+"))
            .map { it.toInt() }
    }

    fun getScore(): Int {
        var totalScore = 0
        winningNumbers.forEach {
            totalScore += 2.0.pow(it - 1).toInt()
        }
        return totalScore
    }

    fun getNumberOfWinnersPerCard(): List<Int> {
        return winningNumbers.toList()
    }

    fun getTotalNumberOfCards(): Int {
        val numOfEachCard = winningNumbers.toList().map { 1 }.toMutableList()
        winningNumbers.forEachIndexed { index, num ->
            for (i in 1..num) {
                val newIndex = index + i
                if (newIndex >= numOfEachCard.size) return@forEachIndexed
                numOfEachCard[newIndex] += 1
            }
        }
        return numOfEachCard.sum()
    }

    private fun calculateWinningNumbers() {
        cards.lines()
            .forEach { card ->
                if (card.isEmpty()) return@forEach

                var winningNumsFound = 0
                getWinningNumbers(card).forEach {
                    if (getDrawnNumbers(card).contains(it)) winningNumsFound++
                }
                winningNumbers.add(winningNumsFound)
            }
    }

    init {
        calculateWinningNumbers()
    }

}