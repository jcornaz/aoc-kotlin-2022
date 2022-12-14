import kotlin.properties.Delegates

object Day14 {

    fun part1(input: String): Long =
        Cave.buildCave(input).countDrops() - 1

    fun part2(input: String): Long =
        Cave.buildCave(input).apply {
            floor = true
        }.countDrops()

    private fun Cave.countDrops(): Long {
        val sequence = generateSequence(this) {
            if (it.dropSand() != null) it else null
        }
        return sequence.count().toLong()
    }

    class Cave private constructor(private val blocked: MutableSet<Position>) {
        var floor: Boolean by Delegates.observable(false) { _, _, _ -> maxY += 2 }
        private var maxY = blocked.maxOf { it.y }

        fun dropSand(): Position? =
            generateSequence(START_POSITION) { it.nextPosition }
                .takeWhile { it.y <= maxY }
                .last()
                .takeIf { it.y < maxY && it != START_POSITION }
                ?.also { blocked.add(it) }

        private val Position.nextPosition: Position?
            get() =
                moves
                    .map { (dx, dy) -> Position(x + dx, y + dy) }
                    .firstOrNull { (x, y) -> !isBlocked(x, y) }

        fun isBlocked(x: Int, y: Int): Boolean {
            if (floor && y >= maxY) return true
            return Position(x, y) in blocked
        }

        companion object {
            private val START_POSITION = Position(500, 0)
            private val moves = sequenceOf(0 to 1, -1 to 1, 1 to 1)

            fun buildCave(input: String): Cave =
                input.lineSequence()
                    .fold(mutableSetOf<Position>()) { blocked, line ->
                        line.split(" -> ")
                            .map(Position::parse)
                            .fold<Position, Position?>(null) { prev, next ->
                                blocked.addAll(if (prev == null) sequenceOf(next) else line(prev, next))
                                next
                            }
                        blocked
                    }
                    .let { Cave(it) }

            private fun line(from: Position, to: Position): Sequence<Position> =
                if (from.x != to.x) {
                    (minOf(from.x, to.x)..maxOf(from.x, to.x)).asSequence().map { Position(it, to.y) }
                } else {
                    (minOf(from.y, to.y)..maxOf(from.y, to.y)).asSequence().map { Position(to.x, it) }
                }
        }
    }

    data class Position(val x: Int, val y: Int) {
        companion object {
            fun parse(input: String): Position {
                val (x, y) = input.split(",")
                return Position(x.toInt(), y.toInt())
            }
        }
    }
}
