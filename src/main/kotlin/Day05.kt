class Almanac(input: String) {

    private val inputParts = input.split("\n\n")
    val seeds = inputParts[0]
        .split(" ")
        .drop(1)
        .map { it.toLong() }
    val soil = getSeedMap(inputParts[1])
    val fertilizer = getSeedMap(inputParts[2])
    val water = getSeedMap(inputParts[3])
    val light = getSeedMap(inputParts[4])
    val temperature = getSeedMap(inputParts[5])
    val humidity = getSeedMap(inputParts[6])
    val location = getSeedMap(inputParts[7])
    private val operations = listOf(soil, fertilizer, water, light, temperature, humidity, location)

    private fun getSeedMap(from: String): SeedMap {
        val seedMapParts = from
            .lines()
            .drop(1)
            .map {
                val map = it.split(" ")
                    .map { num -> num.toLong() }
                SeedMapProps(map[0], map[1], map[2])
            }
        return SeedMap(seedMapParts)
    }

    fun getFinalLocation(seed: Long): Long {
        val result = operations.fold(seed) { acc, next ->
            next.getDestination(acc)
        }
        return result
    }

    fun getLowestLocation(): Long {
        return seeds.map { getFinalLocation(it) }
            .minOf { it }
    }

}

data class SeedMapProps(
    val destination: Long,
    val source: Long,
    val rangeLength: Long
)

data class SeedMap(val maps: List<SeedMapProps>) {

    fun getDestination(from: Long): Long {
        var foundDestination = -1L
        maps.forEach {
            if (from >= it.source && from <= it.source + it.rangeLength) {
                foundDestination = it.destination + from - it.source
            }
        }
        return if (foundDestination >= 0) foundDestination else from
    }

}