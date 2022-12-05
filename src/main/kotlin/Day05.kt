import java.util.*

object Day05 {
    // move 1 from 2 to 1
    fun part1(input: String): String {
        val stacks = parseInitialState(input)
        input.lines().dropWhile { it.isNotBlank() }.drop(1).forEach { instruction ->
            val (n, _, from, _, to) = instruction.removePrefix("move ").split(" ")
            repeat(n.toInt()) {
                stacks[to.toInt() - 1].push(stacks[from.toInt() - 1].pop())
            }
        }

        return stacks
            .map { it.pop() }
            .joinToString(separator = "")
    }

    private fun parseInitialState(input: String): List<Stack<Char>> {
        val indices = generateSequence(1) { it + 4 }
        val initialStateLines = input.lines().takeWhile { "1" !in it }
        return indices
            .takeWhile { it < initialStateLines.first().length }
            .map { i ->
                val stack = Stack<Char>()
                initialStateLines.mapNotNull { line -> line.getOrNull(i)?.takeIf { !it.isWhitespace() } }
                    .forEach(stack::push)
                stack.reverse()
                stack
            }.toList()
    }

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Long = TODO()

}
