import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo


class Day01Test : FunSpec({
    test("part1 should return expected output for the example") {
        Day01.part1(
            """
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
        )
            .shouldBeEqualTo(24_000)
    }

    test("part1 should return expected output for the puzzle input") {
        Day01.part1(Day01Test::class.java.getResource("/day01_input.txt")?.readText().orEmpty().trim())
            .shouldBeEqualTo(69528)
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
})

data class TestCase(
    val input: String,
    val expectedOutput: Long,
    val name: String? = null
)