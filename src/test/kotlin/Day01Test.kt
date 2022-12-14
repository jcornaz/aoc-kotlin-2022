import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val EXAMPLE = """
    1000
    2000
    3000

    4000

    5000
    6000

    7000
    8000
    9000

    10000
""".trimIndent()

private val INPUT = Day01Test::class.java.getResource("/day01_input.txt")?.readText().orEmpty().trim()

class Day01Test : FunSpec({
    context("part 1") {
        test("should return expected output for the example") {
            Day01.part1(EXAMPLE) shouldBeEqualTo 24_000
        }

        test("should return expected output for the puzzle input") {
            Day01.part1(INPUT) shouldBeEqualTo 69528
        }

        listOf<Pair<String, Long>>(
            "42" to 42,
            "2\n3" to 5,
            "2\n3\n\n10" to 10,
            "2\n3\n\n1" to 5,
        ).forEach { (input, expectedOutput) ->
            test("part1(\"${input}\") = $expectedOutput") {
                Day01.part1(input) shouldBeEqualTo expectedOutput
            }
        }
    }

    context("part 2") {

        test("should return expected output for the example") {
            Day01.part2(EXAMPLE) shouldBeEqualTo 45_000
        }

        test("should return expected output for the puzzle input") {
            Day01.part2(INPUT) shouldBeEqualTo 206152
        }

        listOf<Pair<String, Long>>(
            "42" to 42,
            "2\n3" to 5,
            "2\n3\n\n10" to 15,
            "2\n3\n\n10\n\n1\n\n5\n6" to 26,
        ).forEach { (input, expectedOutput) ->
            test("part2(\"${input}\") = $expectedOutput") {
                Day01.part2(input) shouldBeEqualTo expectedOutput
            }
        }
    }
})
