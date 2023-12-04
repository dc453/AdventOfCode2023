private const val symbolMatch = "\\D"

class EngineSchematic(input: String) {

    private val schematic = input.lines()
        .toMutableList()

    private fun checkSymbol(character: Char): Boolean {
        return Regex(symbolMatch).matches(character.toString()) && character != '.'
    }

    fun getNumbers(): List<Int> {
        val numbers: MutableList<SchematicNumber> = mutableListOf()
        schematic.forEachIndexed { yIndex, line ->
            line.split(Regex("\\D+"))
                .forEach { num ->
                    if (num.isNotEmpty()) {
                        val xIndex = schematic[yIndex].indexOf(num)
                        numbers.add(SchematicNumber(xIndex, yIndex, num.toInt()))

                        // account for duplicate numbers in same line
                        val replacementStr = ".".repeat(num.length)
                        schematic[yIndex] = schematic[yIndex].replaceFirst(num, replacementStr)
                    }
                }
        }

        return numbers
            .toList()
            .filter {
                getNumAdjacentSymbols(it) > 0
            }
            .map {
                it.number
            }
    }

    private fun getNumAdjacentSymbols(num: SchematicNumber): Int {
        var numAdjacentSymbols = 0
        if (num.yIndex > 0) {
            if (num.xIndex > 0) {
                if (checkSymbol(schematic[num.yIndex - 1][num.xIndex - 1])) numAdjacentSymbols++
            }
            num.number.toString()
                .forEachIndexed { cIndex, _ ->
                    if (checkSymbol(schematic[num.yIndex - 1][num.xIndex + cIndex])) numAdjacentSymbols++
                }
            if (num.xIndex + num.number.toString().length < schematic[num.yIndex].length) {
                if (checkSymbol(schematic[num.yIndex - 1][num.xIndex + num.number.toString().length])) numAdjacentSymbols++
            }
        }
        if (num.xIndex > 0) {
            if (checkSymbol(schematic[num.yIndex][num.xIndex - 1])) numAdjacentSymbols++
        }
        if (num.xIndex + num.number.toString().length < schematic[num.yIndex].length) {
            if (checkSymbol(schematic[num.yIndex][num.xIndex + num.number.toString().length])) numAdjacentSymbols++
        }
        if (num.yIndex < schematic.size - 1) {
            if (num.xIndex > 0) {
                if (checkSymbol(schematic[num.yIndex + 1][num.xIndex - 1])) numAdjacentSymbols++
            }
            num.number.toString()
                .forEachIndexed { cIndex, _ ->
                    if (checkSymbol(schematic[num.yIndex + 1][num.xIndex + cIndex])) numAdjacentSymbols++
                }
            if (num.xIndex + num.number.toString().length < schematic[num.yIndex].length) {
                if (checkSymbol(schematic[num.yIndex + 1][num.xIndex + num.number.toString().length])) numAdjacentSymbols++
            }
        }
        return numAdjacentSymbols
    }

    fun getTotal(): Int {
        return getNumbers().sum()
    }

}

data class SchematicNumber(
    val xIndex: Int,
    val yIndex: Int,
    val number: Int
)