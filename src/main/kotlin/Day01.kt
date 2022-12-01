object Day01 {
    fun part1(input: String): Long =
        input.splitToSequence("\n\n")
            .map { elf ->
                elf.lineSequence().sumOf { it.toLong() }
            }
            .max()

    fun part2(input: String): Long = TODO()
}