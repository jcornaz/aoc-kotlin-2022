import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val EXAMPLE = """
""".trimIndent()

private val INPUT = Day19Test::class.java.getResource("/day19_input.txt")?.readText().orEmpty().trim()

class Day19Test : FunSpec({
    context("part 1") {
        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the example") {
            Day19.part1(EXAMPLE) shouldBeEqualTo 0
        }

        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the puzzle input") {
            Day19.part1(INPUT) shouldBeEqualTo 0
        }

        listOf<Pair<String, Long>>(
            // TODO Add more test cases here
        ).forEach { (input, expectedOutput) ->
            test("part1(\"${input}\") = $expectedOutput") {
                Day19.part1(input) shouldBeEqualTo expectedOutput
            }
        }
    }

    context("part 2") {
        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the example") {
            Day19.part2(EXAMPLE) shouldBeEqualTo 0
        }

        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the puzzle input") {
            Day19.part2(INPUT) shouldBeEqualTo 0
        }

        listOf<Pair<String, Long>>(
            // TODO Add more test cases here
        ).forEach { (input, expectedOutput) ->
            test("part2(\"${input}\") = $expectedOutput") {
                Day19.part2(input) shouldBeEqualTo expectedOutput
            }
        }
    }
})
