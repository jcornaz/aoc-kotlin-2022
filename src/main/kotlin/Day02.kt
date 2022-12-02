private const val WIN = 6
private const val DRAW = 3
private const val LOSS = 0

object Day02 {

    private val plays = Play.values().asSequence()
    private val results = Result.values().asSequence()

    enum class Play(val symbol: String, val score: Int) {
        Rock("A", 1),
        Paper("B", 2),
        Scissors("C", 3),
    }

    enum class Result(val symbol: String, val score: Int) {
        Lose("X", 0),
        Draw("Y", 3),
        Win("Z", 6),
    }

    fun part1(input: String): Int =
        input.lines().sumOf(::part1ScoreOf)

    fun part2(input: String): Int =
        input.lineSequence().sumOf(::part2ScoreOf)

    private fun parsePlay(input: String): Play = plays.first { it.symbol == input }
    private fun parseResult(input: String): Result = results.first { it.symbol == input }

    private fun part1ScoreOf(line: String): Int {
        val (opponent, me) = line.split(' ')
        return score(
            parsePlay(opponent),
            when (me) {
                "X" -> Play.Rock
                "Y" -> Play.Paper
                else -> Play.Scissors
            }
        )
    }

    private fun part2ScoreOf(line: String): Int {
        val (opponent, wanted) = line.split(' ')
            .let { (op, wanted) -> parsePlay(op) to parseResult(wanted) }
        val me = whatToPlay(opponent, wanted)
        return wanted.score + me.score
    }

    private fun score(opponent: Play, me: Play): Int =
        resultOf(opponent, me).score + me.score

    private fun resultOf(opponent: Play, me: Play): Result =
        results.first { whatToPlay(opponent, it) == me }

    fun whatToPlay(opponent: Play, wantedResult: Result): Play = when (wantedResult) {
        Result.Draw -> opponent
        Result.Win -> when (opponent) {
            Play.Rock -> Play.Paper
            Play.Paper -> Play.Scissors
            Play.Scissors -> Play.Rock
        }
        Result.Lose -> plays.first { whatToPlay(it, Result.Win) == opponent }
    }
}
