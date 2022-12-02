import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val EXAMPLE = """
A Y
B X
C Z
""".trimIndent()

// A | X -> rock (1pt)
// B | Y -> pap er (2pt)
// C | Z -> scissors (3pt)

// A Y   -> rock vs paper -> win (6) + paper (2) = 8
// B X   -> paper vs rock -> loose (0) + paper (1) = 1
// C Z   -> scissors vs scissors -> draw (3) + scissors (3) = 6

private val INPUT_JONATHAN = Day02Test::class.java.getResource("/day02_input.txt")?.readText().orEmpty().trim()
private val INPUT_MARKUS = Day02Test::class.java.getResource("/day02_msc.txt")?.readText().orEmpty().trim()
private val INPUT_ALEX = Day02Test::class.java.getResource("/day02_ar.txt")?.readText().orEmpty().trim()
private val INPUT_ZAYA = Day02Test::class.java.getResource("/day02_rk.txt")?.readText().orEmpty().trim()

class Day02Test : FunSpec({
    context("part 1") {
        test("should return expected output for the example") {
            Day02.part1(EXAMPLE) shouldBeEqualTo 15
        }

        test("should return expected output for the puzzle input") {
            Day02.part1(INPUT_JONATHAN) shouldBeEqualTo 11841
        }

        test("should return expected output for the puzzle input") {
            Day02.part1(INPUT_MARKUS) shouldBeEqualTo 8392
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

        // TODO Set the expected value and enable the test by removing the 'x' prefix
        test("should return expected output for Jonathan puzzle input") {
            Day02.part2(INPUT_JONATHAN) shouldBeEqualTo 13022
        }

        // TODO Set the expected value and enable the test by removing the 'x' prefix
        test("should return expected output for Markus puzzle input") {
            Day02.part2(INPUT_MARKUS) shouldBeEqualTo 10116
        }

        listOf(
            "A X" to "Z",
            "A Y" to "X",
            "A Z" to "Y",
            "B X" to "X",
            "B Y" to "Y",
            "B Z" to "Z",
            "C X" to "Y",
            "C Y" to "Z",
            "C Z" to "X"
        ).forEach { (line, expectedValue) ->
            test("whatToPlay($line) = $expectedValue") {
                Day02.part2WhatToPlay(line) shouldBeEqualTo expectedValue
            }
        }
    }
})
