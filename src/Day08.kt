fun main() {
    fun createNetwork(input: List<String>, networkPattern: String) = input
        .drop(2)
        .map { Regex(networkPattern).findAll(it).map { it.value } }
        .associate { seq ->
            val (a, b, c) = seq.toList()
            a to (b to c)
        }

    fun part1(input: List<String>): Int {
        val instructions = input.first()
        val network = createNetwork(input, "[A-Z][A-Z][A-Z]")

        var current = "AAA"

        0.rangeTo(Int.MAX_VALUE).forEach {
            current = if ('L' == instructions[it % instructions.length]) network[current]!!.first else network[current]!!.second
            if ("ZZZ" == current) {
                return it + 1
            }
        }

        return 0
    }

    fun part2(input: List<String>): Long {
        val instructions = input.first()
        val network = createNetwork(input, "[A-Z0-9][A-Z0-9][A-Z0-9]")

        val stepsPerPath = mutableMapOf<Int, Long>()
        var currentNodes = network.keys.filter { it.endsWith('A') }

        0.rangeTo(Int.MAX_VALUE).forEach {
            val nextNodes = mutableListOf<String>()
            for ((i, current) in currentNodes.withIndex()) {
                val position = it % instructions.length
                val instruction = instructions[position]
                val node = network[current]!!
                val nextNode = if (instruction == 'L') node.first else node.second
                if (nextNode.endsWith('Z')) {
                    stepsPerPath[i] = it + 1L
                    if (stepsPerPath.size == currentNodes.size) {
                        return stepsPerPath.values.leastCommonMultiple()
                    }
                }
                nextNodes.add(nextNode)
            }
            currentNodes = nextNodes
        }

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")

    check(part1(testInput) == 6)
    check(part2(testInput) == 6L)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}