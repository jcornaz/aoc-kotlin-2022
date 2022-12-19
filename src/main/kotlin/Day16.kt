import java.util.*

private typealias ValveId = String

object Day16 {

    private val LINE_REGEX = Regex("Valve ([A-Z]+) has flow rate=(\\d+); tunnels? leads? to valves? (.+)")

    fun part1(input: String, time: Int = 30): Int {
        val valves = input.lineSequence()
            .map(::parseValve)
            .toMap()
        val solver = Solver(maxStep = time, initialState = SearchState(valves.keys.first(), valves.mapValues { false }, 0), valves = valves)
        return solver.solve()
    }

    private fun parseValve(input: String): Pair<ValveId, Valve> {
        val (_, valve, rate, edges) =LINE_REGEX.find(input)?.groupValues ?: error("failed to match input line: $input")
        return valve to Valve(rate.toInt(), edges.split(", "))
    }

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Long = TODO()


    data class Valve(val rate: Int, val connections: Collection<ValveId>)

    class SearchState(val valve: ValveId, val openValves: Map<ValveId, Boolean>, val step: Int) {

        override fun equals(other: Any?): Boolean {
            if (other !is SearchState) return false
            return valve == other.valve && openValves == other.openValves
        }

        override fun hashCode(): Int = valve.hashCode() + 31 * openValves.hashCode()
    }

    class Solver(val maxStep: Int, private val initialState: SearchState, private val valves: Map<ValveId, Valve>) {

        private val visitedStates: MutableSet<SearchState> = mutableSetOf()

        private val priorityQueue: PriorityQueue<Pair<SearchState, Int>> = PriorityQueue<Pair<SearchState, Int>>(compareBy { -it.second })
            .apply { add(initialState to 0) }

        private fun getSuccessorState(searchState: SearchState, score: Int): Sequence<Pair<SearchState, Int>> {
            if (searchState.step >= maxStep) return emptySequence()
            val currentValve = valves[searchState.valve] ?: return emptySequence()

            val nextStep = searchState.step + 1
            val connectedStates = currentValve.connections.asSequence().map {
                SearchState(it, searchState.openValves, nextStep) to score
            }

            return if(searchState.openValves[searchState.valve] == false && currentValve.rate != 0)
                connectedStates + (SearchState(searchState.valve, searchState.openValves + (searchState.valve to true), nextStep) to computeScore(score, nextStep, currentValve.rate))
            else
                connectedStates
        }

        private fun computeScore(score: Int, step: Int, rate: Int) = score + ((maxStep - step) * rate)

        fun solve() : Int {
            var bestStateThusFar = initialState to 0

            while (!priorityQueue.isEmpty()) {
                val (state, score) = priorityQueue.poll()
                visitedStates.add(state)

                if (bestStateThusFar.second < score) bestStateThusFar = state to score

                getSuccessorState(state, score)
                    .filter { it.first !in visitedStates }
                    .forEach { priorityQueue.add(it) }
            }

            return bestStateThusFar.second
        }
    }
}
