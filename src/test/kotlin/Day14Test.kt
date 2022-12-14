import io.kotest.core.spec.style.FunSpec
import Day14.Cave
import org.amshove.kluent.*
import kotlin.test.assertFails

private val EXAMPLE = """
498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9
""".trimIndent()

private val INPUT = Day14Test::class.java.getResource("/day14_input.txt")?.readText().orEmpty().trim()

class Day14Test : FunSpec({
    context("part 1") {
        test("should return expected output for the example") {
            Day14.part1(EXAMPLE) shouldBeEqualTo 24
        }

        test("should return expected output for the puzzle input") {
            Day14.part1(INPUT) shouldBeEqualTo 793
        }

        listOf(
            "1,1" to 0L,
            "499,2 -> 501,2" to 1L,
            "498,10 -> 502,10" to 4L,
            "497,10 -> 503,10" to 9L,
        ).forEach { (input, expected) ->
            test("part1($input) = $expected") {
                Day14.part1(input).shouldBeEqualTo(expected)
            }
        }

        test("should detect obstacle") {
            val cave: Cave = Cave.buildCave("2,0")
            cave.isBlocked(2, 0).shouldBeTrue()
        }

        test("should detect empty space cave") {
            val cave: Cave = Cave.buildCave("2,0")
            cave.isBlocked(3,0).shouldBeFalse()
        }

        test("should detect obstacles in a line") {
            val cave: Cave = Cave.buildCave("2,0 -> 4,0")
            cave.isBlocked(2, 0).shouldBeTrue()
            cave.isBlocked(3, 0).shouldBeTrue()
            cave.isBlocked(4, 0).shouldBeTrue()
        }

        test("should detect obstacles in a reverse line ") {
            val cave: Cave = Cave.buildCave("2,0 -> 0,0")
            cave.isBlocked(2, 0).shouldBeTrue()
            cave.isBlocked(1, 0).shouldBeTrue()
            cave.isBlocked(0, 0).shouldBeTrue()
        }

        test("should detect obstacles in a vertical line") {
            val cave: Cave = Cave.buildCave("2,0 -> 2,2")
            cave.isBlocked(2, 0).shouldBeTrue()
            cave.isBlocked(2, 1).shouldBeTrue()
            cave.isBlocked(2, 2).shouldBeTrue()
        }

        test("should detect obstacles in a reversed vertical line") {
            val cave: Cave = Cave.buildCave("2,2 -> 2,0")
            cave.isBlocked(2, 0).shouldBeTrue()
            cave.isBlocked(2, 1).shouldBeTrue()
            cave.isBlocked(2, 2).shouldBeTrue()
        }

        test("should detect obstacles in rock formation with direction change") {
            val cave: Cave = Cave.buildCave("2,0 -> 2,2 -> 4,2")
            cave.isBlocked(2, 0).shouldBeTrue()
            cave.isBlocked(2, 1).shouldBeTrue()
            cave.isBlocked(2, 2).shouldBeTrue()
            cave.isBlocked(3, 2).shouldBeTrue()
            cave.isBlocked(4, 2).shouldBeTrue()
        }

        test("should detect obstacles in two rock formations") {
            val cave: Cave = Cave.buildCave("2,0 -> 2,2\n12,0 -> 12,2")
            cave.isBlocked(2, 0).shouldBeTrue()
            cave.isBlocked(2, 1).shouldBeTrue()
            cave.isBlocked(2, 2).shouldBeTrue()
            cave.isBlocked(2, 3).shouldBeFalse()
            cave.isBlocked(12, 0).shouldBeTrue()
            cave.isBlocked(12, 1).shouldBeTrue()
            cave.isBlocked(12, 2).shouldBeTrue()
        }

        test("should detect obstacles in example") {
            val cave: Cave = Cave.buildCave(EXAMPLE)
            cave.isBlocked(502, 5).shouldBeTrue()
        }

        listOf(
            Triple("400,2 -> 600,2", 1, listOf(500 to 1)),
            Triple("400,2 -> 600,2", 1, listOf(500 to 1)),
            Triple("400,2 -> 600,2", 2, listOf(500 to 1, 499 to 1)),
            Triple("400,2 -> 600,2", 3, listOf(500 to 1, 499 to 1, 501 to 1)),
        ).forEach { (input, n, expectedBlocked) ->
            test("sand simulation for $input and $n steps without overflow") {
                val cave = Cave.buildCave(input)
                repeat(n) { cave.dropSand().shouldNotBeNull() }
                expectedBlocked.forEach { (x, y) ->
                    cave.isBlocked(x,y).shouldBeTrue()
                }
            }
        }

        test("sand simulation detects overflow") {
            val cave = Cave.buildCave("1,1")
            cave.dropSand().shouldBeNull()
        }
    }

    context("part 2") {
        test("should return expected output for the example") {
            Day14.part2(EXAMPLE) shouldBeEqualTo 93
        }

        test("should return expected output for the puzzle input") {
            Day14.part2(INPUT) shouldBeEqualTo 24166
        }
    }
})
