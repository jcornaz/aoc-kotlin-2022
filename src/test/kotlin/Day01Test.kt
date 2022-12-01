import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo
import java.io.File


class Day01Test : FunSpec({
    val EXAMPLE = ""
    val INPUT = Day01Test::class.java.getResource("/day01_input.txt")?.readText().orEmpty().trim()

    listOf<Pair<String, Long>>(
        // EXAMPLE to 0,
        // INPUT to 0,
    ).forEach { (input, expectedOutput) ->
        test("part1(\"$input\") = $expectedOutput") {
            Day01.part1(input) shouldBeEqualTo expectedOutput
        }
    }
})