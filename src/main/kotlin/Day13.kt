import java.lang.StringBuilder

object Day13 {

    fun part1(input: String): Int =
        input.split("\n\n").withIndex()
            .filter { (_, both) ->
                isInCorrectOrder(both.lines().first(), both.lines().last())!!
            }
            .sumOf { it.index + 1 }

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Int {
        val dividers = listOf("[[2]]", "[[6]]")
        val sorted = (input.lines() + dividers)
            .filter { it.isNotBlank() }
            .sortedWith { left, right ->
                isInCorrectOrder(left, right)
                    ?.let { if (it) -1 else 1 }
                    ?: 0
            }
        return dividers.map { sorted.indexOf(it) + 1 }.fold(1) { a, b -> a * b }
    }

    fun isInCorrectOrder(left: String, right: String): Boolean? {
        return if (left.startsWith("[") || right.startsWith("[")) {
            val leftArray = left.toArray()
            val rightArray = right.toArray()
            leftArray.zip(rightArray).forEach { (a, b) ->
                isInCorrectOrder(a, b)?.let { return it }
            }
            isInCorrectOrder(leftArray.size, rightArray.size)
        } else {
            isInCorrectOrder(left.toInt(), right.toInt())
        }
    }

    private fun isInCorrectOrder(left: Int, right: Int): Boolean? =
        if (left < right) true else if (left > right) false else null

    private fun String.toArray(): List<String> {
        if (!startsWith("[")) return listOf(this)
        return buildList {
            var nestedLevel = 0
            val current = StringBuilder()
            removePrefix("[")
                .forEach { char ->
                    if ((char == ',' || char == ']') && nestedLevel == 0 && current.isNotBlank()) {
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
        }
    }
}