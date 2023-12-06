class Almanac(input: String, private val isSeedRange: Boolean = false) {

    private val inputParts = input.split("\n\n")
    val seeds = getSeeds(inputParts[0])
    val soil = getSeedMap(inputParts[1])
    val fertilizer = getSeedMap(inputParts[2])
    val water = getSeedMap(inputParts[3])
    val light = getSeedMap(inputParts[4])
    val temperature = getSeedMap(inputParts[5])
    val humidity = getSeedMap(inputParts[6])
    val location = getSeedMap(inputParts[7])
    private val operations = listOf(soil, fertilizer, water, light, temperature, humidity, location)

    private fun getSeeds(from: String): List<Long> {
        val seedParts = from
            .split(" ")
            .drop(1)
        val seeds: MutableList<Long> = mutableListOf()
        if (isSeedRange) {
            seedParts.windowed(2, 2) {
                for (i in 1..it[1].toLong()) {
                    seeds.add(it[0].toLong() + i)
                }
            }
        } else {
            seedParts.forEach {
                seeds.add(it.toLong())
            }
        }
        return seeds
    }

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
        return seeds
            .map { getFinalLocation(it) }
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
            if (from >= it.source && from <= it.source + it.rangeLength - 1) {
                foundDestination = it.destination + from - it.source
            }
        }
        return if (foundDestination >= 0) foundDestination else from
    }

}