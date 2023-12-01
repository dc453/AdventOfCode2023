import java.io.File

fun main(args: Array<String>) {

    // Day 1
    val day1Input = File("src/main/inputs/Day01.txt").readText()
    val calibrationDocument = CalibrationDocument()
    val day1Part1 = calibrationDocument.getCalibrationValue(day1Input)
    println("Day 1, part 1: $day1Part1")


}