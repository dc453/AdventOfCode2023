import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertEquals

class Day01_Tests {

    private lateinit var calibrationDocument: CalibrationDocument

    @BeforeTest
    fun setup() {
        calibrationDocument = CalibrationDocument()
    }

    @Test
    fun `should get calibration value of line`() {
        val value = calibrationDocument.getCalibrationValueOfLine("1twothree4")
        assertEquals(14, value)
    }

    @Test
    fun `should get calibration value while filtering out alpha characters`() {
        val value = calibrationDocument.getCalibrationValueOfLine("1two3four")
        assertEquals(13, value)
    }

    @Test
    fun `should get calibration values of multiple lines`() {
        val document = "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet"

        val values = calibrationDocument.getCalibrationValuesOfLines(document)

        assertEquals(listOf(12, 38, 15, 77), values)
    }

    @Test
    fun `should calculate calibration value of document`() {
        val document = "1abc2\n" +
                "pqr3stu8vwx\n" +
                "a1b2c3d4e5f\n" +
                "treb7uchet"

        val calibrationValue = calibrationDocument.getCalibrationValue(document)

        assertEquals(142, calibrationValue)
    }

}