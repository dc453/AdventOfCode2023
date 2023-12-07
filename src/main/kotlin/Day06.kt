class BoatRace(private val time: Long, private val distance: Long) {

    fun getDistance(chargeTime: Long): Long {
        return (time - chargeTime) * chargeTime
    }

    fun getRecordScenarios(): Long {
        var numScenarios = 0L
        for (i in 1..time) {
            if (getDistance(i) > distance) numScenarios++
        }
        return numScenarios
    }

}

class BoatRaces(input: String, private val enableKerningFix: Boolean = false) {

    val races: MutableList<BoatRace> = mutableListOf()

    fun getMarginOfError(): Long {
        return races.map { it.getRecordScenarios() }
            .reduce { acc, i -> i * acc }
    }

    private fun convertLine(line: String): List<Long> {
        val delimiter = if (enableKerningFix) "\\:" else "\\s+"
        return line.split(Regex(delimiter))
            .drop(1)
            .map { it.replace(" ", "").toLong() }
    }

    init {
        val lines = input.split("\n")
        val times = convertLine(lines[0])
        val distances = convertLine(lines[1])
        times.forEachIndexed { index, time ->
            races.add(BoatRace(time, distances[index]))
        }
    }

}