import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day06Tests {

    @Test
    fun `should get distance of boat from charge time`() {
        val race = BoatRace(7, 9)
        val result = race.getDistance(5)
        assertEquals(10, result)
    }

    @Test
    fun `should count number of ways to beat record`() {
        val race = BoatRace(7, 9)
        val result = race.getRecordScenarios()
        assertEquals(4, result)
    }

    @Test
    fun `should determine races`() {
        val boatRaces = BoatRaces("Time:      7  15   30\n" +
                "Distance:  9  40  200")
        assertEquals(3, boatRaces.races.size)
    }

    @Test
    fun `should calculate margin of error across all races`() {
        val boatRaces = BoatRaces("Time:      7  15   30\n" +
                "Distance:  9  40  200")
        val result = boatRaces.getMarginOfError()
        assertEquals(288, result)
    }

    @Test
    fun `should calculate margin of error when kerning fix enabled`() {
        val boatRaces = BoatRaces("Time:      7  15   30\n" +
                "Distance:  9  40  200", true)
        val result = boatRaces.getMarginOfError()
        assertEquals(71503, result)
    }

}