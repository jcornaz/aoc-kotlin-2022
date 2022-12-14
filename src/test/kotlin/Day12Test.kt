import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val EXAMPLE = """
Sabqponm
abcryxxl
accszExk
acctuvwj
abdefghi
""".trimIndent()

private val EXAMPLE_PATH =  """
v..v<<<<
>v.vv<<^
.>vv>E^^
..v>>>^^
..>>>>>^
""".trimIndent()

private val INPUT = Day12Test::class.java.getResource("/day12_input.txt")?.readText().orEmpty().trim()

class Day12Test : FunSpec({
    context("part 1") {
        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the example") {
            Day12.part1(EXAMPLE) shouldBeEqualTo 31
        }

        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the puzzle input") {
            Day12.part1(INPUT) shouldBeEqualTo 0
        }

        xtest("map direction for example") {
            Day12.findPath(EXAMPLE) shouldBeEqualTo EXAMPLE_PATH
        }

        listOf<Pair<String, String>>(
            "SbcdefghijklmnopqrstuvwxyE" to ">>>>>>>>>>>>>>>>>>>>>>>>>E",
            "SbcdefghijklmnopqrstuvwxyE".reversed() to "E<<<<<<<<<<<<<<<<<<<<<<<<<",
        ).forEach { (input, expectedOutput) ->
            test("part1(\"${input}\") = $expectedOutput") {
                Day12.findPath(input) shouldBeEqualTo expectedOutput
            }
        }
    }

    context("part 2") {
        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the example") {
            Day12.part2(EXAMPLE) shouldBeEqualTo 0
        }

        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the puzzle input") {
            Day12.part2(INPUT) shouldBeEqualTo 0
        }

        listOf<Pair<String, Long>>(
            // TODO Add more test cases here
        ).forEach { (input, expectedOutput) ->
            test("part2(\"${input}\") = $expectedOutput") {
                Day12.part2(input) shouldBeEqualTo expectedOutput
            }
        }
    }
})
