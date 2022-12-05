import java.util.*

object Day05 {

    fun part1(input: String): String {
        val stacks = input.initialState()
        input.instructions().forEach { instruction ->
            repeat(instruction.count) {
                stacks[instruction.to].push(stacks[instruction.from].pop())
            }
        }
        return topOfStackToString(stacks)
    }

    fun part2(input: String): String {
        val stacks = input.initialState()
        input.instructions().forEach { instruction ->
            (1..instruction.count)
                .map { stacks[instruction.from].pop() }
                .reversed()
                .forEach { stacks[instruction.to].push(it) }
        }
        return topOfStackToString(stacks)
    }

    private fun topOfStackToString(stacks: List<Stack<Char>>): String =
        stacks
            .map { it.pop() }
            .joinToString(separator = "")

    private fun String.initialState(): List<Stack<Char>> {
        val indices = generateSequence(1) { it + 4 }
        val initialStateLines = lines().takeWhile { "1" !in it }
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

    private fun String.instructions(): Sequence<Instruction> =
        lineSequence()
            .dropWhile { it.isNotBlank() }
            .drop(1)
            .map {
                val (n, _, from, _, to) = it.removePrefix("move ").split(" ")
                Instruction(n.toInt(), from.toInt() - 1, to.toInt() - 1)
            }

}

data class Instruction(val count: Int, val from: Int, val to: Int)
