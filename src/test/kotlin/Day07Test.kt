import io.kotest.core.spec.style.FunSpec
import org.amshove.kluent.shouldBeEqualTo

private val EXAMPLE = """
$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k
""".trimIndent()

private val INPUT = Day07Test::class.java.getResource("/day07_input.txt")?.readText().orEmpty().trim()

class Day07Test : FunSpec({
    context("part 1") {
        test("should return expected output for the example") {
            Day07.part1(EXAMPLE) shouldBeEqualTo 95437
        }

        test("should return expected output for the puzzle input") {
            Day07.part1(INPUT) shouldBeEqualTo 1453349
        }
    }

    context("part 2") {
        test("should return expected output for the example") {
            Day07.part2(EXAMPLE) shouldBeEqualTo 24933642
        }

        test("should return expected output for the puzzle input") {
            Day07.part2(INPUT) shouldBeEqualTo 2948823
        }
    }
})
