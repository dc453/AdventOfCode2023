import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Tests {

    @Test
    fun `should get calibration value of line`() {
        val calibrationDocument = CalibrationDocument()
        val value = calibrationDocument.getCalibrationValueOfLine("1twothree4")
        assertEquals(14, value)
    }

    @Test
    fun `should get calibration value while filtering out alpha characters`() {
        val calibrationDocument = CalibrationDocument()
        val value = calibrationDocument.getCalibrationValueOfLine("1two3foo")
        assertEquals(13, value)
    }

    @Test
    fun `should convert spelled out numbers to integers when enabled`() {
        val calibrationDocument = CalibrationDocument(true)

        var value = calibrationDocument.getCalibrationValueOfLine("one99two")
        assertEquals(12, value)

        value = calibrationDocument.getCalibrationValueOfLine("three99four")
        assertEquals(34, value)

        value = calibrationDocument.getCalibrationValueOfLine("five99six")
        assertEquals(56, value)

        value = calibrationDocument.getCalibrationValueOfLine("seven99eight")
        assertEquals(78, value)

        value = calibrationDocument.getCalibrationValueOfLine("nine11zero")
        assertEquals(90, value)
    }

    @Test
    fun `should recognize overlapping words when converting numbers`() {
        val calibrationDocument = CalibrationDocument(true)
        val value = calibrationDocument.getCalibrationValueOfLine("eightwothree")
        assertEquals(83, value)
    }

    @Test
    fun `should not convert spelled out numbers to integers when not enabled`() {
        val calibrationDocument = CalibrationDocument()
        val value = calibrationDocument.getCalibrationValueOfLine("one99two")
        assertEquals(99, value)
    }

    @Test
    fun `should get calibration values of multiple lines`() {
        val document = "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet"
        val calibrationDocument = CalibrationDocument()

        val values = calibrationDocument.getCalibrationValuesOfLines(document)

        assertEquals(listOf(12, 38, 15, 77), values)
    }

    @Test
    fun `should calculate calibration value of document`() {
        val document = "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet"
        val calibrationDocument = CalibrationDocument()

        val calibrationValue = calibrationDocument.getCalibrationValue(document)

        assertEquals(142, calibrationValue)
    }

}