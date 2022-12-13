import java.lang.StringBuilder

object Day13 {

    fun part1(input: String): Int =
        input.trim().split("\n\n").map { it.trim() }.withIndex()
            .filter { (_, both) ->
                val r = isInCorrectOrder(both.lines().first(), both.lines().last())
                println("$both -> $r")
                r
            }
            .sumOf { it.index + 1 }

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Long = TODO()

    fun isInCorrectOrder(left: String, right: String): Boolean {
        return if (left.startsWith("[") || right.startsWith("[")) {
            val l = left.toArray()
            val r = right.toArray()
            l.size <= r.size && l.zip(r).all { (a, b) -> isInCorrectOrder(a, b) }
        } else {
            left.toInt() <= right.toInt()
        }
    }

    private fun String.toArray(): List<String> {
        if (!startsWith("[")) return listOf(this)
        return buildList {
            var nestedLevel = 0
            var current = StringBuilder()
            removePrefix("[")
                .removeSuffix("]")
                .forEach { char ->
                    if (char == ',' && nestedLevel == 0) {
                        add(current.toString())
                        current.clear()
                    } else {
                        current.append(char)
                        if (char == '[') {
                            nestedLevel += 1
                        } else if (char == ']') {
                            nestedLevel -= 1
                        }
                    }
                }
            if (current.isNotEmpty()) {
                add(current.toString())
            }
        }
    }
}