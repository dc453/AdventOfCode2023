class Almanac(input: String) {

    private val inputParts = input.split("\n\n")
    val seeds = inputParts[0]
        .split(" ")
        .drop(1)
        .map { it.toInt() }
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
                    .map { num -> num.toInt() }
                SeedMapProps(map[0], map[1], map[2])
            }
        return SeedMap(seedMapParts)
    }

    fun getFinalLocation(seed: Int): Int {
        val result = operations.fold(seed) { acc, next ->
            next.getDestination(acc)
        }
        return result
    }

    fun getLowestLocation(): Int {
        return seeds.map { getFinalLocation(it) }
            .minOf { it }
    }

}

data class SeedMapProps(
    val destination: Int,
    val source: Int,
    val rangeLength: Int
)

data class SeedMap(val maps: List<SeedMapProps>) {

    private val destinationRange = (0..99).toMutableList()

    fun getDestination(from: Int): Int {
        maps.forEach {
            for (i in 0..<it.rangeLength) {
                destinationRange[it.source + i] = it.destination + i
            }
        }
        return destinationRange[from]
    }

}