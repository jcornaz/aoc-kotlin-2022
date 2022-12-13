import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val EXAMPLE = """
    [1,1,3,1,1]
    [1,1,5,1,1]

    [[1],[2,3,4]]
    [[1],4]

    [9]
    [[8,7,6]]

    [[4,4],4,4]
    [[4,4],4,4,4]

    [7,7,7,7]
    [7,7,7]

    []
    [3]

    [[[]]]
    [[]]

    [1,[2,[3,[4,[5,6,7]]]],8,9]
    [1,[2,[3,[4,[5,6,0]]]],8,9]
""".trimIndent()

private val INPUT = Day13Test::class.java.getResource("/day13_input.txt")?.readText().orEmpty().trim()

class Day13Test : FunSpec({
    context("part 1") {
        xtest("should return expected output for the example") {
            Day13.part1(EXAMPLE) shouldBeEqualTo 13
        }

        xtest("should return expected output for the puzzle input") {
            Day13.part1(INPUT) shouldBeEqualTo 0
        }

        listOf(
            ("[1]" to "[2]") to true,
            ("[2]" to "[1]") to false,
            ("[1,2]" to "[2,3]") to true,
            ("[1]" to "[2,3]") to true,
            ("[1,2]" to "[2]") to false,
            ("[3,2]" to "[2]") to false,
            ("[1]" to "[[2]]") to true,
            ("[[1]]" to "[2]") to true,
            ("[]" to "[3]") to true,
            ("[[[]]]" to "[[]]") to false,
            ("[]" to "[[]]") to true,
            ("[1,1,3,1,1]" to "[1,1,5,1,1]") to true,
            ("[[4,4],4,4]" to "[[4,4],4,4,4]") to true,
        ).forEach { (input, expectedOutput) ->
            test("isInRightOrder($input) = $expectedOutput") {
                Day13.isInCorrectOrder(input.first, input.second) shouldBeEqualTo expectedOutput
            }
        }
    }

    context("part 2") {
        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the example") {
            Day13.part2(EXAMPLE) shouldBeEqualTo 0
        }

        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the puzzle input") {
            Day13.part2(INPUT) shouldBeEqualTo 0
        }

        listOf<Pair<String, Long>>(
            // TODO Add more test cases here
        ).forEach { (input, expectedOutput) ->
            test("part2(\"${input}\") = $expectedOutput") {
                Day13.part2(input) shouldBeEqualTo expectedOutput
            }
        }
    }
})
