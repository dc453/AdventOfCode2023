class CalibrationDocument(val enableConvertNumbers: Boolean = false) {

    fun getCalibrationValueOfLine(line: String): Int {
        val convertedLine = if (enableConvertNumbers) {
            convertNumbers(line)
        } else {
            line
        }
        val filteredNumbers = convertedLine
            .filter { it.isDigit() }
        return "${filteredNumbers[0]}${filteredNumbers[filteredNumbers.lastIndex]}".toInt()
    }

    private fun convertNumbers(line: String): String {
        // keep first & last char to match other numbers
        return line
            .replace("one", "o1e")
            .replace("two", "t2o")
            .replace("three", "t3e")
            .replace("four", "f4r")
            .replace("five", "f5e")
            .replace("six", "s6x")
            .replace("seven", "s7n")
            .replace("eight", "e8t")
            .replace("nine", "n9e")
            .replace("zero", "z0o")
    }

    fun getCalibrationValuesOfLines(lines: String): List<Int> {
        return lines.split("\n")
            .map { getCalibrationValueOfLine(it) }
    }

    fun getCalibrationValue(document: String): Int {
        return getCalibrationValuesOfLines(document).sum()
    }

}