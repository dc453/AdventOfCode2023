import kotlin.math.pow

class ScratchCard(private val cards: String) {

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
        cards.lines()
            .forEach { card ->
                val winningNums = getWinningNumbers(card)
                val drawnNums = getDrawnNumbers(card)
                var winningNumsFound = 0
                winningNums.forEach {
                    if (drawnNums.contains(it)) winningNumsFound++
                }
                totalScore += 2.0.pow(winningNumsFound - 1).toInt()
            }
        return totalScore
    }

}