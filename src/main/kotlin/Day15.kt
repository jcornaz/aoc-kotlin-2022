import javax.swing.text.Position
import kotlin.math.abs

object Day15 {

    fun part1(input: String): Long = numberOfNonBeacon(input, 2000000).toLong()

    fun part2(input: String, searchScope: Int): Int {
        val search = Search(input)
        for (x in 0..searchScope) {
            for (y in 0..searchScope) {
                val distressBeacon = Position(x, y)
                if (search.canBeDistressBeacon(distressBeacon))
                    return distressBeacon.x * 4_000_000 + distressBeacon.y
            }
        }
        return -1
    }

    private val LINE_REGEX = Regex("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)")

    fun numberOfNonBeacon(input: String, y: Int): Int {
        val search = Search(input)
        val minX = search.sensors.minOf { it.minX }
        val maxX = search.sensors.maxOf { it.maxX }
        return (minX..maxX)
            .asSequence()
            .map { Position(it, y) }
            .count { it !in search.beacons && !search.canBeDistressBeacon(it) }
    }

    class Search(input: String) {
        val beacons = mutableSetOf<Position>()
        val sensors = input.lines()
            .map {
                val (sensorPosition, beaconPosition) = parseLine(it)
                beacons.add(beaconPosition)
                Sensor(sensorPosition, sensorPosition.distanceTo(beaconPosition))
            }

        fun canBeDistressBeacon(position: Position): Boolean =
            sensors.none { sensor -> sensor.isWithinRange(position) }
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
