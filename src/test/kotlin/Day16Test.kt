import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val EXAMPLE = """
Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
Valve BB has flow rate=13; tunnels lead to valves CC, AA
Valve CC has flow rate=2; tunnels lead to valves DD, BB
Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
Valve EE has flow rate=3; tunnels lead to valves FF, DD
Valve FF has flow rate=0; tunnels lead to valves EE, GG
Valve GG has flow rate=0; tunnels lead to valves FF, HH
Valve HH has flow rate=22; tunnel leads to valve GG
Valve II has flow rate=0; tunnels lead to valves AA, JJ
Valve JJ has flow rate=21; tunnel leads to valve II    
""".trimIndent()

private val INPUT = Day16Test::class.java.getResource("/day16_input.txt")?.readText().orEmpty().trim()

class Day16Test : FunSpec({
    context("part 1") {
        xtest("should return expected output for the example") {
            Day16.part1(EXAMPLE) shouldBeEqualTo 1651
        }

        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the puzzle input") {
            Day16.part1(INPUT) shouldBeEqualTo 0
        }

        listOf(
            """
                Valve AA has flow rate=0; tunnels lead to valves BB
                Valve BB has flow rate=13; tunnels lead to valves AA
            """.trimIndent() to (13*28),
            """
                Valve AA has flow rate=0; tunnels lead to valves BB, CC
                Valve BB has flow rate=13; tunnels lead to valves CC, AA
                Valve CC has flow rate=2; tunnels lead to valves AA, BB
            """.trimIndent() to ((13*28) + (2*26)),
            """
                Valve AA has flow rate=0; tunnels lead to valves BB, CC
                Valve BB has flow rate=13; tunnels lead to valves AA
                Valve CC has flow rate=2; tunnels lead to valves BB
            """.trimIndent() to ((13*28) + (2*25)),
        ).forEach { (input, expectedOutput) ->
            test("part1(\"${input}\") = $expectedOutput") {
                Day16.part1(input) shouldBeEqualTo expectedOutput
            }
        }


        listOf(
            1 to 0,
            2 to 0,
            3 to 20,
            4 to 40,
            5 to 63,
            6 to 93,
            // 30 to 1651,
        ).forEach { (time, expectedOutput) ->
            test("part1(EXAMPLE, $time) = $expectedOutput") {
                Day16.part1(EXAMPLE, time) shouldBeEqualTo expectedOutput
            }
        }
    }

    context("part 2") {
        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the example") {
            Day16.part2(EXAMPLE) shouldBeEqualTo 0
        }

        // TODO Set the expected value and enable the test by removing the 'x' prefix
        xtest("should return expected output for the puzzle input") {
            Day16.part2(INPUT) shouldBeEqualTo 0
        }

        listOf<Pair<String, Long>>(
            // TODO Add more test cases here
        ).forEach { (input, expectedOutput) ->
            test("part2(\"${input}\") = $expectedOutput") {
                Day16.part2(input) shouldBeEqualTo expectedOutput
            }
        }
    }
})
