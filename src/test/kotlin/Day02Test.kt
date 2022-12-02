import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo
import Day02.Play
import Day02.Result

private val EXAMPLE = """
A Y
B X
C Z
""".trimIndent()

private val INPUT = Day02Test::class.java.getResource("/day02_input.txt")?.readText().orEmpty().trim()

class Day02Test : FunSpec({
    context("part 1") {
        test("should return expected output for the example") {
            Day02.part1(EXAMPLE) shouldBeEqualTo 15
        }

        test("should return expected output for the puzzle input") {
            Day02.part1(INPUT) shouldBeEqualTo 11841
        }

        listOf(
            "A X" to 3 + 1, // draw
            "A Y" to 6 + 2, // win
            "A Z" to 0 + 3, // lose
            "B X" to 0 + 1, // lose
            "B Y" to 3 + 2, // draw
            "B Z" to 6 + 3, // win
            "C X" to 6 + 1, // win
            "C Y" to 0 + 2, // lose
            "C Z" to 3 + 3, // draw
        ).forEach { (line, expectedValue) ->
            test("scoreOf($line) = $expectedValue") {
                Day02.part1(line) shouldBeEqualTo expectedValue
            }
        }
    }

    context("part 2") {
        test("should return expected output for the example") {
            Day02.part2(EXAMPLE) shouldBeEqualTo 12
        }

        test("should return expected output for the puzzle input") {
            Day02.part2(INPUT) shouldBeEqualTo 13022
        }

        listOf(
            ((Play.Rock to Result.Lose) to Play.Scissors),
            ((Play.Paper to Result.Lose) to Play.Rock),
            ((Play.Scissors to Result.Lose) to Play.Paper),
            ((Play.Rock to Result.Win) to Play.Paper),
            ((Play.Paper to Result.Win) to Play.Scissors),
            ((Play.Scissors to Result.Win) to Play.Rock),
            ((Play.Rock to Result.Draw) to Play.Rock),
            ((Play.Scissors to Result.Draw) to Play.Scissors),
            ((Play.Paper to Result.Draw) to Play.Paper),
        ).forEach { (entry, shouldPlay) ->
            val (opponentPlay, wantedResult) = entry
            test("Must play $shouldPlay against $opponentPlay in order to $wantedResult") {
                Day02.whatToPlay(opponentPlay, wantedResult) shouldBeEqualTo shouldPlay
            }
        }
    }
})
