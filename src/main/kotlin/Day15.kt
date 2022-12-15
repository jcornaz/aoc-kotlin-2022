import kotlin.math.abs

object Day15 {

    fun part1(input: String): Long = numberOfNonBeacon(input, 2000000).toLong()

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Long = TODO()

    private val LINE_REGEX = Regex("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)")

    fun numberOfNonBeacon(input: String, y: Int): Int {
        val beacons = mutableSetOf<Position>()
        val sensors = input.lines()
            .map {
                val (sensorPosition, beaconPosition) = parseLine(it)
                beacons.add(beaconPosition)
                Sensor(sensorPosition, sensorPosition.distanceTo(beaconPosition))
            }
        val minX = sensors.minOf { it.minX }
        val maxX = sensors.maxOf { it.maxX }
        return (minX..maxX)
            .map { Position(it, y) }
            .count { it !in beacons && sensors.any { sensor -> sensor.isWithinRange(it) } }
    }

    private fun parseLine(line: String): Pair<Position, Position> {
        val (_, sx, sy, bx, by) = LINE_REGEX.find(line)!!.groupValues
        return Position(sx.toInt(), sy.toInt()) to Position(bx.toInt(), by.toInt())
    }

    data class Position(val x: Int, val y: Int) {
        fun distanceTo(other: Position): Int {
            return abs(x - other.x) + abs(y - other.y)
        }
    }

    data class Sensor(val position: Position, val range: Int) {
        val minX = position.x - range
        val maxX = position.x + range
        fun isWithinRange(otherPosition: Position) = position.distanceTo(otherPosition) <= range
    }

}
