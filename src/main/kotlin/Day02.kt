private const val WIN = 6
private const val DRAW = 3
private const val LOSS = 0

object Day02 {


    @Suppress("UNUSED_PARAMETER")
    fun part1(input: String): Int = input.lines().sumOf {
        scoreOf(it)
    }

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Int =
        input.lineSequence()
            .map { "${it.first()} ${part2WhatToPlay(it)}" }
            .sumOf { scoreOf(it) }

    private fun scoreOf(line: String): Int = when (line) {
        "A X" -> DRAW + 1
        "A Y" -> WIN + 2
        "A Z" -> LOSS + 3
        "B X" -> LOSS + 1
        "B Y" -> DRAW + 2
        "B Z" -> WIN + 3
        "C X" -> WIN + 1
        "C Y" -> LOSS + 2
        "C Z" -> DRAW + 3
        else -> -1
    }

    fun part2WhatToPlay(line: String): String = when (line) {
        "A X" -> "Z"
        "A Y" -> "X"
        "A Z" -> "Y"
        "B X" -> "X"
        "B Y" -> "Y"
        "B Z" -> "Z"
        "C X" -> "Y"
        "C Y" -> "Z"
        "C Z" -> "X"
        else -> ""
    }


}
