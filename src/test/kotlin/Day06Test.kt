import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val INPUT = Day06Test::class.java.getResource("/day06_input.txt")?.readText().orEmpty().trim()

class Day06Test : FunSpec({
    context("part 1") {
        test("should return expected output for the puzzle input") {
            Day06.part1(INPUT) shouldBeEqualTo 1766
        }

        listOf(
            "" to -1,
            "abcd" to 4,
            "abcde" to 4,
            "aabcde" to 5,
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 7,
        ).forEach { (input, expectedOutput) ->
            test("part1(\"${input}\") = $expectedOutput") {
                Day06.part1(input) shouldBeEqualTo expectedOutput
            }
        }
    }

    context("part 2") {
        test("should return expected output for the puzzle input") {
            Day06.part2(INPUT) shouldBeEqualTo 2383
        }

        listOf(
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb" to 19,
            "bvwbjplbgvbhsrlpgdmjqwftvncz" to 23,
        ).forEach { (input, expectedOutput) ->
            test("part2(\"${input}\") = $expectedOutput") {
                Day06.part2(input) shouldBeEqualTo expectedOutput
            }
        }
    }
})
