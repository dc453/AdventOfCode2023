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

    private fun getSeedMap(from: String): List<SeedMap> {
        return from
            .lines()
            .drop(1)
            .map {
                val map = it.split(" ")
                    .map { num -> num.toInt() }
                SeedMap(map[0], map[1], map[2])
            }
    }

}

data class SeedMap(val destination: Int, val source: Int, val rangeLength: Int) {

}