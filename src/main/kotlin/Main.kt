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

}