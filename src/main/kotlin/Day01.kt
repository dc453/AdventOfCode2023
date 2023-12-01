class CalibrationDocument() {

    fun getCalibrationValueOfLine(line: String): Int {
        val filteredNumbers = line.filter { it.isDigit() }
        return "${filteredNumbers[0]}${filteredNumbers[filteredNumbers.lastIndex]}".toInt()
    }

    fun getCalibrationValuesOfLines(lines: String): List<Int> {
        return lines.split("\n")
            .map { getCalibrationValueOfLine(it) }
    }

    fun getCalibrationValue(document: String): Int {
        return getCalibrationValuesOfLines(document).sum()
    }

}