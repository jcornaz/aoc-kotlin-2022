import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

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
