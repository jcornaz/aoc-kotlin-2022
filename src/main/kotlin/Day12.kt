object Day12 {

    @Suppress("UNUSED_PARAMETER")
    fun part1(input: String): Long = TODO()

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Long = TODO()

    private const val START_CHARACTER = 'S'
    private const val GOAL_CHARACTER = 'E'

    fun findPath(input: String): String {
        val map = parseMap(input)
        var currentState = findInitialState(map)

        val maxY = map.size - 1
        val maxX = map.first().size - 1

        val outputMap: List<MutableList<Char>> = map.map { it.mapTo(mutableListOf()) { '.' } }

        while (map[currentState.y][currentState.x] != GOAL_CHARACTER) {
            print(map[currentState.y][currentState.x])
            val (direction, nextState) = getSuccessors(currentState, maxX, maxY)
//                .filter { (_, coordinate) ->
//                    map[coordinate.y][coordinate.x] == GOAL_CHARACTER || map[coordinate.y][coordinate.x] - map[currentState.y][currentState.x] <= 1
//                }
                .maxBy { (_, coordinate) ->
                    val value = map[coordinate.y][coordinate.x]
                    if (value == GOAL_CHARACTER) 'z' + 1 else value
                }
            outputMap[currentState.y][currentState.x] = direction.char
            currentState = nextState
        }

        outputMap[currentState.y][currentState.x] = GOAL_CHARACTER

        return outputMap.joinToString(separator = "\n") { it.joinToString(separator = "")}
    }

    private fun parseMap(input: String): List<List<Char>> = input.lines().map { it.toList() }

    private fun findInitialState(map: List<List<Char>>) : Coordinate =
        map.withIndex()
            .map { (row, l) ->
                Coordinate(l.withIndex().find { (_, c) -> c == START_CHARACTER }!!.index, row)
            }
            .first()

    private fun getSuccessors(currentCoordinate: Coordinate, maxX: Int, maxY: Int) = Direction.values()
        .map { it to it.getNextSquare(currentCoordinate) }
        .filter { (_, target) -> target.x in (0 .. maxX) && target.y in (0 .. maxY) }

    class Coordinate(val x: Int, val y: Int)

    enum class Direction(val char: Char) {
        UP('^') {
            override fun getNextSquare(currentCoordinate: Coordinate) =
                Coordinate(currentCoordinate.x, currentCoordinate.y - 1)
        },
        DOWN('v') {
            override fun getNextSquare(currentCoordinate: Coordinate) =
                Coordinate(currentCoordinate.x, currentCoordinate.y + 1)
        },
        LEFT('<') {
            override fun getNextSquare(currentCoordinate: Coordinate): Coordinate =
                Coordinate(currentCoordinate.x - 1, currentCoordinate.y)
        },
        RIGHT('>') {
            override fun getNextSquare(currentCoordinate: Coordinate) =
                Coordinate(currentCoordinate.x + 1, currentCoordinate.y)
        };
        
        abstract fun getNextSquare(currentCoordinate: Coordinate) : Coordinate
    }
}
