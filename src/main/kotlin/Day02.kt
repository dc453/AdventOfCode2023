private const val gamePartsDelimiter = ": "
private const val gameRoundDelimiter = "; "
private const val roundPartsDelimiter = ", "
private const val cubeInfoDelimiter = " "

class CubeBag(private val red: Int, private val green: Int, private val blue: Int) {

    fun getResult(game: String): Int {
        val gameParts = game.split(gamePartsDelimiter)
        val gameRounds = gameParts[1].split(gameRoundDelimiter)
            .map { round ->
                getRoundResult(round)
            }
        return if (!gameRounds.contains(false)) {
            gameParts[0].split(cubeInfoDelimiter)[1].toInt()
        } else {
            0
        }
    }

    fun getRoundResult(round: String): Boolean {
        val possibleResults = round.split(roundPartsDelimiter)
            .map { cube ->
                val cubeInfo = cube.split(cubeInfoDelimiter)
                val cubeVal = cubeInfo[0].toInt()
                when (cubeInfo[1]) {
                    "red" -> cubeVal <= red
                    "green" -> cubeVal <= green
                    "blue" -> cubeVal <= blue
                    else -> true
                }
            }
        return !possibleResults.contains(false)
    }

    fun play(games: String, powerMode: Boolean = false): Int {
        return games.lines()
            .sumOf {
                if (powerMode) {
                    getPowerOfFewestCubes(it)
                } else {
                    getResult(it)
                }
            }
    }

    fun getPowerOfFewestCubes(game: String): Int {
        val fewestCubes = Cube(0, 0, 0)
        val gameParts = game.split(gamePartsDelimiter)
        gameParts[1].split(gameRoundDelimiter)
            .map { round ->
                round.split(roundPartsDelimiter)
                    .map { cube ->
                        val cubeInfo = cube.split(cubeInfoDelimiter)
                        val cubeVal = cubeInfo[0].toInt()
                        when {
                            cubeInfo[1] == "red" && cubeVal > fewestCubes.r -> fewestCubes.r = cubeVal
                            cubeInfo[1] == "green" && cubeVal > fewestCubes.g -> fewestCubes.g = cubeVal
                            cubeInfo[1] == "blue" && cubeVal > fewestCubes.b -> fewestCubes.b = cubeVal
                        }
                    }
            }
        return fewestCubes.r * fewestCubes.g * fewestCubes.b
    }

}

data class Cube(
    var r: Int,
    var g: Int,
    var b: Int
) {}