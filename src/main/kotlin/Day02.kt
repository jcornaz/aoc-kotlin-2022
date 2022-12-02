import com.sun.jdi.Value

private const val WIN = 6
private const val DRAW = 3
private const val LOSS = 0

object Day02 {

    enum class Play(val symbol: String) {
        Rock("A"),
        Paper("B"),
        Scissors("C"),
    }

    enum class Result(val symbol: String) {
        Lose("X"),
        Draw("Y"),
        Win("Z"),
    }

    fun part1(input: String): Int = input.lines().sumOf {
        scoreOf(it)
    }

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

    fun part2WhatToPlay(line: String): String {
        val (op, wanted) = line.split(" ")
        val shouldPlay = whatToPlay(
            Play.values().first { it.symbol == op },
            Result.values().first { it.symbol == wanted }
        )
        return when (shouldPlay) {
            Play.Rock -> "X"
            Play.Paper -> "Y"
            Play.Scissors -> "Z"
        }
    }

    fun whatToPlay(opponent: Play, wantedResult: Result): Play = when (wantedResult) {
        Result.Draw -> opponent
        Result.Win -> when (opponent) {
            Play.Rock -> Play.Paper
            Play.Paper -> Play.Scissors
            Play.Scissors -> Play.Rock
        }
        Result.Lose -> Play.values().first { whatToPlay(it, Result.Win) == opponent }
    }
}
