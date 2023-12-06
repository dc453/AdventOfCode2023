import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.math.exp

class Day05Tests {

    val input = "seeds: 79 14 55 13\n" +
            "\n" +
            "seed-to-soil map:\n" +
            "50 98 2\n" +
            "52 50 48\n" +
            "\n" +
            "soil-to-fertilizer map:\n" +
            "0 15 37\n" +
            "37 52 2\n" +
            "39 0 15\n" +
            "\n" +
            "fertilizer-to-water map:\n" +
            "49 53 8\n" +
            "0 11 42\n" +
            "42 0 7\n" +
            "57 7 4\n" +
            "\n" +
            "water-to-light map:\n" +
            "88 18 7\n" +
            "18 25 70\n" +
            "\n" +
            "light-to-temperature map:\n" +
            "45 77 23\n" +
            "81 45 19\n" +
            "68 64 13\n" +
            "\n" +
            "temperature-to-humidity map:\n" +
            "0 69 1\n" +
            "1 0 69\n" +
            "\n" +
            "humidity-to-location map:\n" +
            "60 56 37\n" +
            "56 93 4"

    @Test
    fun `should know seed values`() {
        val almanac = Almanac(input)
        assertEquals(listOf(79, 14, 55, 13), almanac.seeds)
    }

    @Test
    fun `should know seed-to-soil values`() {
        val almanac = Almanac(input)
        val expected = SeedMap(listOf(
            SeedMapProps(50, 98, 2),
            SeedMapProps(52, 50, 48)
        ))
        assertEquals(expected, almanac.soil)
    }

    @Test
    fun `should know soil-to-fertilizer values`() {
        val almanac = Almanac(input)
        val expected = SeedMap(listOf(
            SeedMapProps(0, 15, 37),
            SeedMapProps(37, 52, 2),
            SeedMapProps(39, 0, 15)
        ))
        assertEquals(expected, almanac.fertilizer)
    }

    @Test
    fun `should know fertilizer-to-water values`() {
        val almanac = Almanac(input)
        val expected = SeedMap(listOf(
            SeedMapProps(49, 53, 8),
            SeedMapProps(0, 11, 42),
            SeedMapProps(42, 0, 7),
            SeedMapProps(57, 7, 4)
        ))
        assertEquals(expected, almanac.water)
    }

    @Test
    fun `should know water-to-light values`() {
        val almanac = Almanac(input)
        val expected = SeedMap(listOf(
            SeedMapProps(88, 18, 7),
            SeedMapProps(18, 25, 70)
        ))
        assertEquals(expected, almanac.light)
    }

    @Test
    fun `should know light-to-temperature values`() {
        val almanac = Almanac(input)
        val expected = SeedMap(listOf(
            SeedMapProps(45, 77, 23),
            SeedMapProps(81, 45, 19),
            SeedMapProps(68, 64, 13)
        ))
        assertEquals(expected, almanac.temperature)
    }

    @Test
    fun `should know temperature-to-humidity values`() {
        val almanac = Almanac(input)
        val expected = SeedMap(listOf(
            SeedMapProps(0, 69, 1),
            SeedMapProps(1, 0, 69)
        ))
        assertEquals(expected, almanac.humidity)
    }

    @Test
    fun `should know humidity-to-location values`() {
        val almanac = Almanac(input)
        val expected = SeedMap(listOf(
            SeedMapProps(60, 56, 37),
            SeedMapProps(56, 93, 4)
        ))
        assertEquals(expected, almanac.location)
    }

    @Test
    fun `should get destination range of source`() {
        val seedMap = SeedMap(listOf(
            SeedMapProps(50, 98, 2)
        ))
        assertEquals(51, seedMap.getDestination(99))
    }
}