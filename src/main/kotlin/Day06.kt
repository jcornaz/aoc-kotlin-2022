object Day06 {

    @Suppress("UNUSED_PARAMETER")
    fun part1(input: String) = findSignalPosition(input, 4)


    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Int = findSignalPosition(input, 14)

    private fun findSignalPosition(input: String, n: Int) = input.windowedSequence(n)
        .withIndex()
        .firstOrNull() { (_, v) ->
            v.toSet().size == v.length
        }?.let { it.index + n } ?: -1
}
