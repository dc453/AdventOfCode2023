import java.io.File

fun main(args: Array<String>) {

    // Day 1
    val day1Input = File("src/main/inputs/Day01.txt").readText()
    val calibrationDocumentPart1 = CalibrationDocument()
    val day1Part1 = calibrationDocumentPart1.getCalibrationValue(day1Input)
    println("Day 1, part 1: $day1Part1")
    val calibrationDocumentPart2 = CalibrationDocument(enableConvertNumbers = true)
    val day1Part2 = calibrationDocumentPart2.getCalibrationValue(day1Input)
    println("Day 1, part 2: $day1Part2")

    // Day 2
    val day2Input = File("src/main/inputs/Day02.txt").readText()
    val cubeBag = CubeBag(12, 13, 14)
    val day2Part1 = cubeBag.play(day2Input)
    println("Day 2, part 1: $day2Part1")
    val day2Part2 = cubeBag.play(day2Input, true)
    println("Day 2, part 2: $day2Part2")

    // Day 3
    val day3Input = File("src/main/inputs/Day03.txt").readText()
    val engineSchematic = EngineSchematic(day3Input)
    val day3Part1 = engineSchematic.getTotal()
    println("Day 3, part 1: $day3Part1")

    // Day 4
    val day4Input = File("src/main/inputs/Day04.txt").readText()
    val card = ScratchCard(day4Input)
    val day4Part1 = card.getScore()
    println("Day 4, part 1: $day4Part1")

}