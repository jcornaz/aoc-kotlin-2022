import java.util.*

object Day16 {

    fun part1(input: String): Long {
        TODO()
    }

    @Suppress("UNUSED_PARAMETER")
    fun part2(input: String): Long = TODO()

    data class Valve(val id: String, val rate: Int)

    data class SearchState(val valve: Valve, val openValves: Map<Valve, Boolean>)

    sealed class Action {
        object OpenValve : Action()
        data class GotoValve(val name: String): Action()
    }

    class Solver(private val initialState: SearchState, private val connections: Map<Valve, Collection<Valve>>) {

        private val visitedStates: MutableSet<SearchState> = mutableSetOf()

        private val priorityQueue: PriorityQueue<Pair<SearchState, Int>> = PriorityQueue<Pair<SearchState, Int>>(compareBy { -it.second })
            .apply { add(initialState to 0) }

        private fun getSuccessorState(searchState: SearchState, score: Int, step: Int): Sequence<Pair<SearchState, Int>> {
            val connectedStates = connections[searchState.valve]?.asSequence().orEmpty().map {
                SearchState(it, searchState.openValves) to score
            }

            return if(searchState.openValves[searchState.valve] == false && searchState.valve.rate != 0)
                connectedStates + (SearchState(searchState.valve, searchState.openValves + (searchState.valve to true)) to computeScore(score, step, searchState.valve.rate))
            else
                connectedStates
        }

        private fun computeScore(score: Int, step: Int, rate: Int) = score + ((30 - step) * rate)

        fun solve() : Int {
            var bestStateThusFar = initialState to 0
            var step = 0

            while (!priorityQueue.isEmpty() && step <= 30) {
                step++
                val (state, score) = priorityQueue.poll()
                if (bestStateThusFar.second < score) bestStateThusFar = state to score
                getSuccessorState(state, score, step)
                    .forEach { priorityQueue.add(it) }
            }

            return bestStateThusFar.second
        }
    }
}
