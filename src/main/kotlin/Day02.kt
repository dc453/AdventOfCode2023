class CubeBag(private val red: Int, private val green: Int, private val blue: Int) {

    fun getResult(game: String): Int {
        val gameParts = game.split(": ")
        val gameSets = gameParts[1].split("; ")
            .map { round ->
                getRoundResult(round)
            }
        return if (!gameSets.contains(false)) {
            gameParts[0].split(" ")[1].toInt()
        } else {
            0
        }
    }

    fun getRoundResult(round: String): Boolean {
        val possibleResults = round.split(", ")
            .map { cube ->
                val cubeInfo = cube.split(" ")
                when (cubeInfo[1]) {
                    "red" -> cubeInfo[0].toInt() <= red
                    "green" -> cubeInfo[0].toInt() <= green
                    "blue" -> cubeInfo[0].toInt() <= blue
                    else -> true
                }
            }
        return !possibleResults.contains(false)
    }

    fun play(games: String): Int {
        return games.lines()
            .sumOf {
                getResult(it)
            }
    }

}