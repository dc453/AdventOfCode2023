class BoatRace(private val time: Int, private val distance: Int) {

    fun getDistance(chargeTime: Int): Int {
        return (time - chargeTime) * chargeTime
    }

    fun getRecordScenarios(): Int {
        var numScenarios = 0
        for (i in 1..time) {
            if (getDistance(i) > distance) numScenarios++
        }
        return numScenarios
    }

}

class BoatRaces(input: String) {

    val races: MutableList<BoatRace> = mutableListOf()

    fun getMarginOfError(): Int {
        return races.map { it.getRecordScenarios() }
            .reduce { acc, i -> i * acc }
    }

    init {
        val lines = input.split("\n")
        val times = lines[0]
            .split(Regex("\\s+"))
            .drop(1)
        val distances = lines[1]
            .split(Regex("\\s+"))
            .drop(1)
        times.forEachIndexed { index, time ->
            races.add(BoatRace(time.toInt(), distances[index].toInt()))
        }
    }

}