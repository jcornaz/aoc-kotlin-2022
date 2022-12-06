object Day06 {

    @Suppress("UNUSED_PARAMETER")
    fun part1(input: String) =
        input.windowedSequence(4)
            .withIndex()
            .first { (_, v) ->
                v.toSet().size == v.length
            }.index + 4

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Long = TODO()

}
