import kotlin.math.abs

object Day15 {

    fun part1(input: String): Long = numberOfNonBeacon(input, 2000000).toLong()

    fun part2(input: String, searchScope: Int): Long {
        val scan = Scan.parse(input)
        val beacon = (0..searchScope)
            .asSequence()
            .map { y -> y to scan.uncoveredRange(y, 0..searchScope) }
            .first { (_, range) -> range.size > 0 }
            .let { (y, range) -> range.asSequence().map { Position(it, y) } }
            .first()
        return beacon.x.toLong() * 4_000_000 + beacon.y.toLong()
    }

    private val LINE_REGEX = Regex("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)")

    fun numberOfNonBeacon(input: String, y: Int): Int {
        val scan = Scan.parse(input)
        val searchRange = scan.minX..scan.maxX
        val uncovered = scan.uncoveredRange(y, scan.minX..scan.maxX)
        return searchRange.size - uncovered.size - scan.beacons.count { it.y == y }
    }

    private class Scan private constructor(val beacons: Set<Position>, private val sensors: List<Sensor>) {

        val minX get() = sensors.minOf { it.minX }
        val maxX get() = sensors.maxOf { it.maxX }

        fun uncoveredRange(y: Int, initialRange: IntRange): SearchRange =
            sensors.fold(SearchRange(initialRange)) { range, sensor ->
                sensor.coverage(y)?.let(range::remove) ?: range
            }

        companion object {
            fun parse(input: String): Scan {
                val beacons = mutableSetOf<Position>()
                val sensors = mutableListOf<Sensor>()
                input.lineSequence()
                    .forEach { line ->
                        val (sensorPosition, beaconPosition) = parseLine(line)
                        beacons.add(beaconPosition)
                        sensors.add(Sensor(sensorPosition, sensorPosition.distanceTo(beaconPosition)))
                    }
                return Scan(beacons, sensors)
            }
        }
    }

    private fun parseLine(line: String): Pair<Position, Position> {
        val (_, sx, sy, bx, by) = LINE_REGEX.find(line)!!.groupValues
        return Position(sx.toInt(), sy.toInt()) to Position(bx.toInt(), by.toInt())
    }

    private data class Position(val x: Int, val y: Int) {
        fun distanceTo(other: Position): Int {
            return abs(x - other.x) + abs(y - other.y)
        }
    }

    private data class Sensor(val position: Position, val range: Int) {
        val minX = position.x - range
        val maxX = position.x + range

        operator fun contains(otherPosition: Position) =
            coverage(otherPosition.y)?.let { otherPosition.x in it } ?: false

        fun coverage(y: Int): IntRange? {
            val vDist = abs(y - position.y)
            if (vDist > range) return null
            val extent = range - vDist
            return (position.x - extent)..(position.x + extent)
        }
    }

    private class SearchRange private constructor(val ranges: List<IntRange>) {
        constructor(range: IntRange) : this(listOf(range))

        val size: Int get() = ranges.sumOf { it.size }

        fun asSequence() = ranges.asSequence().flatMap { it.asSequence() }

        fun remove(range: IntRange): SearchRange {
            if (ranges.isEmpty()) return this
            return SearchRange(ranges.flatMap { it.remove(range) })
        }

        private fun IntRange.remove(other: IntRange): List<IntRange> = buildList {
            (first .. minOf(other.first - 1, last))
                .takeUnless { it.isEmpty() }
                ?.let(::add)
            (maxOf(other.last + 1, first)..endInclusive)
                .takeUnless { it.isEmpty() }
                ?.let(::add)
        }
    }

    val IntRange.size: Int get() = last - first + 1
}
