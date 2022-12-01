object Day01 {
    fun part1(input: String): Long =
        input.elfSequence()
            .max()

    fun part2(input: String): Long =
        input.elfSequence()
            .sortedDescending()
            .take(3)
            .sum()

    private fun String.elfSequence() =
        splitToSequence("\n\n")
            .map { elf ->
                elf.lineSequence().sumOf { it.toLong() }
            }
}