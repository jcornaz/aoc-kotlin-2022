import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val EXAMPLE = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
""".trimIndent()

private val INPUT = Day05Test::class.java.getResource("/day05_input.txt")?.readText().orEmpty().trim()

class Day05Test : FunSpec({
    context("part 1") {
        listOf(
            """
                [N]
                 1 
            """.trimIndent() to "N",
            """
                [C]
                [N]
                 1 
            """.trimIndent() to "C",
            """
                [C] [A]
                 1   2
            """.trimIndent() to "CA",
            """
                [B]    
                [C] [A]
                 1   2
            """.trimIndent() to "BA",
            """
               |     [B]
               | [C] [A]
               |  1   2
            """.trimMargin("| ") to "CB",
            """
               |     [D]    
               | [N] [C]    
               | [Z] [M] [P]
               |  1   2   3 
            """.trimMargin("| ") to "NDP",
            """
               |     [D]     
               | [N] [C]    
               | [Z] [M] [P]
               |  1   2   3
               | 
               | move 1 from 2 to 1
            """.trimMargin("| ") to "DCP",
            """
               |     [D]     
               | [N] [C]    
               | [Z] [M] [P]
               |  1   2   3
               | 
               | move 2 from 2 to 3
            """.trimMargin("| ") to "NMC",
        ).forEach { (input, expectedOutput) ->
            test("topOf(\"${input}\") = $expectedOutput") {
                Day05.part1(input) shouldBeEqualTo expectedOutput
            }
        }

        test("should return expected output for the example") {
            Day05.part1(EXAMPLE) shouldBeEqualTo "CMZ"
        }

        test("should return expected output for the puzzle input") {
            Day05.part1(INPUT) shouldBeEqualTo "TGWSMRBPN"
        }
    }

    context("part 2") {
        test("should return expected output for the example") {
            Day05.part2(EXAMPLE) shouldBeEqualTo "MCD"
        }

        test("should return expected output for the puzzle input") {
            Day05.part2(INPUT) shouldBeEqualTo "TZLTLWRNF"
        }

        listOf(
            """
                [N]
                 1 
            """.trimIndent() to "N",
            """
                [C]
                [N]
                 1 
            """.trimIndent() to "C",
            """
                [C] [A]
                 1   2
            """.trimIndent() to "CA",
            """
                [B]    
                [C] [A]
                 1   2
            """.trimIndent() to "BA",
            """
               |     [B]
               | [C] [A]
               |  1   2
            """.trimMargin("| ") to "CB",
            """
               |     [D]    
               | [N] [C]    
               | [Z] [M] [P]
               |  1   2   3 
            """.trimMargin("| ") to "NDP",
            """
               |     [D]     
               | [N] [C]    
               | [Z] [M] [P]
               |  1   2   3
               | 
               | move 1 from 2 to 1
            """.trimMargin("| ") to "DCP",
            """
               |     [D]    
               | [N] [C]    
               | [Z] [M] [P]
               |  1   2   3
               | 
               | move 2 from 2 to 3
            """.trimMargin("| ") to "NMD",
        ).forEach { (input, expectedOutput) ->
            test("part2(\"${input}\") = $expectedOutput") {
                Day05.part2(input) shouldBeEqualTo expectedOutput
            }
        }
    }
})
