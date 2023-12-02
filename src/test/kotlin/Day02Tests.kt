import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Tests {

    @Test
    fun `should determine if round is possible`() {
        val cubeBag = CubeBag(12, 13, 14)
        val result = cubeBag.getRoundResult("3 blue, 4 red")
        assertEquals(true, result)
    }

    @Test
    fun `should determine if round is not possible`() {
        val cubeBag = CubeBag(12, 13, 14)
        val result = cubeBag.getRoundResult("8 green, 6 blue, 20 red")
        assertEquals(false, result)
    }

    @Test
    fun `should calculate score if game is possible`() {
        val cubeBag = CubeBag(12, 13, 14)
        val result = cubeBag.getResult("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green")
        assertEquals(1, result)
    }

    @Test
    fun `should determine if game is not possible`() {
        val cubeBag = CubeBag(12, 13, 14)
        val result = cubeBag.getResult("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red")
        assertEquals(0, result)
    }

    @Test
    fun `should calculate score of games`() {
        val cubeBag = CubeBag(12, 13, 14)
        val result = cubeBag.play("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
        assertEquals(8, result)
    }

}