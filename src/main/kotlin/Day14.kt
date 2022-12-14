object Day14 {

    fun part1(input: String): Long {
        val sequence = generateSequence(Cave.buildCave(input)) {
            if (it.dropSand() != null) it else null
        }
        return sequence.count().toLong() - 1
    }

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Long = TODO()

    class Cave private constructor(private val blockedSpaces : MutableSet<Position>) {

        private val maxY = blockedSpaces.maxOf { it.y }

        override fun toString(): String {
            val xRange = blockedSpaces.minOf { it.x }..blockedSpaces.maxOf { it.x }
            val yRange = blockedSpaces.minOf { it.y }..maxY
            for (y in yRange) {
                for (x in xRange) {
                    print(if (isBlocked(x, y)) '#' else ' ')
                }
                println()
            }
            return super.toString()
        }

        fun dropSand(): Position? {
            return generateSequence(Position(500, 0)) { it.getNextPosition() }
                .takeWhile { it.y <= maxY }
                .last()
                .takeIf { it.y < maxY }
                ?.also { blockedSpaces.add(it) }
        }


        private fun Position.getNextPosition(): Position? {
            if (!isBlocked(x, y + 1)) return Position(x, y + 1)
            if (!isBlocked(x - 1, y + 1)) return Position(x - 1, y + 1)
            if (!isBlocked(x + 1, y + 1)) return Position(x + 1, y + 1)
            return null
        }

        fun isBlocked(x: Int, y: Int): Boolean = Position(x, y) in blockedSpaces

        companion object {

            fun buildCave(input: String): Cave {
                val set = mutableSetOf<Position>()
                input.lines().forEach { line ->
                    line.split(" -> ")
                        .map {
                            val (x, y) = it.split(",")
                            Position(x.toInt(), y.toInt())
                        }
                        .fold<Position, Position?>(null) { prev, next ->
                            if (prev == null) {
                                set.add(next)
                                next
                            } else {
                                if (prev.x != next.x) {
                                    for (x in minOf( prev.x, next.x)..maxOf( prev.x, next.x)) {
                                        set.add(Position(x, next.y))
                                    }
                                } else {
                                    for (y in minOf( prev.y, next.y)..maxOf( prev.y, next.y)) {
                                        set.add(Position(next.x, y))
                                    }
                                }
                                next
                            }
                        }
                }
                return Cave(set)
            }
        }
    }

    data class Position(val x: Int, val y: Int)
}
