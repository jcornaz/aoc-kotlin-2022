import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val EXAMPLE = """
Sensor at x=2, y=18: closest beacon is at x=-2, y=15
Sensor at x=9, y=16: closest beacon is at x=10, y=16
Sensor at x=13, y=2: closest beacon is at x=15, y=3
Sensor at x=12, y=14: closest beacon is at x=10, y=16
Sensor at x=10, y=20: closest beacon is at x=10, y=16
Sensor at x=14, y=17: closest beacon is at x=10, y=16
Sensor at x=8, y=7: closest beacon is at x=2, y=10
Sensor at x=2, y=0: closest beacon is at x=2, y=10
Sensor at x=0, y=11: closest beacon is at x=2, y=10
Sensor at x=20, y=14: closest beacon is at x=25, y=17
Sensor at x=17, y=20: closest beacon is at x=21, y=22
Sensor at x=16, y=7: closest beacon is at x=15, y=3
Sensor at x=14, y=3: closest beacon is at x=15, y=3
Sensor at x=20, y=1: closest beacon is at x=15, y=3
""".trimIndent()

private val INPUT = Day15Test::class.java.getResource("/day15_input.txt")?.readText().orEmpty().trim()

class Day15Test : FunSpec({
    context("part 1") {
        test("should return expected output for the example") {
            Day15.numberOfNonBeacon(EXAMPLE, 10) shouldBeEqualTo 26
        }

        test("should return expected output for the puzzle input") {
            Day15.part1(INPUT) shouldBeEqualTo 4560025
        }

        ///
        ///    S   SB
        ///   ###  #
        ///    B
        listOf(
            ("Sensor at x=0, y=0: closest beacon is at x=2, y=0" to 0) to 4,
            ("Sensor at x=0, y=0: closest beacon is at x=-2, y=0" to 0) to 4,
            ("Sensor at x=0, y=0: closest beacon is at x=0, y=2" to 1) to 3,
            ("""
                Sensor at x=0, y=0: closest beacon is at x=0, y=2
                Sensor at x=4, y=0: closest beacon is at x=5, y=0
            """.trimIndent() to 1) to 4,
        ).forEach { (case, expectedOutput) ->
            test("part1(\"${case}\") = $expectedOutput") {
                Day15.numberOfNonBeacon(case.first, case.second) shouldBeEqualTo expectedOutput
            }
        }
    }

    context("part 2") {
        test("should return expected output for the example") {
            Day15.part2(EXAMPLE, 20) shouldBeEqualTo 56000011
        }

        xtest("should return expected output for the puzzle input") {
            Day15.part2(INPUT, 4000000) shouldBeEqualTo 0
        }
    }
})
