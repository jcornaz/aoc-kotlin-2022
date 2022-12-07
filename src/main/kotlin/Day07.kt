import java.io.File
import java.util.StringJoiner

private const val DISK_SPACE = 70000000
private const val NEEDED_SPACE = 30000000

object Day07 {

    fun part1(input: String): Int {
        val directories = scanDirectory(input)
        return directories.values.filter { it <= 100_000 }.sum()
    }

    fun part2(input: String): Int {
        val directories = scanDirectory(input)
        val used = directories[listOf(" /")] ?: 0
        val needToFree = NEEDED_SPACE - (DISK_SPACE - used)
        return directories.values.sorted().dropWhile { it < needToFree  }.first()
    }

    private fun scanDirectory(input: String): MutableMap<List<String>, Int> {
        val currentPath = mutableListOf<String>()
        val directories = mutableMapOf<List<String>, Int>()
        input.lineSequence()
            .forEach { line ->
                if (line == "\$ cd ..") {
                    currentPath.removeLast()
                } else if (line.startsWith("\$ cd")) {
                    currentPath += line.removePrefix("\$ cd")
                } else if (line.matches(Regex("^\\d+.*"))) {
                    val size = line.split(" ").first().toInt()
                    currentPath.indices.forEach { i ->
                        val dirPath = (0..i).map { currentPath[it] }
                        directories[dirPath] = (directories[dirPath] ?: 0) + size
                    }
                }
            }
        return directories
    }

}