object Day13 {

    fun part1(input: String): Int =
        input.trim().split("\n\n").map { it.trim() }.withIndex()
            .filter { (_, both) ->
                val r = isInCorrectOrder(both.lines().first() to both.lines().last())
                println("$both -> $r")
                r
            }
            .sumOf { it.index + 1 }

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Long = TODO()

    fun isInCorrectOrder(pair: Pair<String, String>): Boolean {
        val (left, right) = pair
        return if (left.startsWith("[") && right.startsWith("[")) {
            val l = left.removePrefix("[").removeSuffix("]").split(",").filter { it.isNotBlank() }
            val r = right.removePrefix("[").removeSuffix("]").split(",").filter { it.isNotBlank() }
            l.zip(r).all { (a, b) -> isInCorrectOrder(a to b) } && l.size <= r.size
        } else if (left.startsWith("[")){
            isInCorrectOrder(left to "[$right]")
        } else if (right.startsWith("[")) {
            isInCorrectOrder("[$left]" to right)
        } else {
            left.toInt() <= right.toInt()
        }
    }
}