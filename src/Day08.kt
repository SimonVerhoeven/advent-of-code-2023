fun main() {
    fun createNetwork(input: List<String>) = input
        .drop(2)
        .map { Regex("[A-Z][A-Z][A-Z]").findAll(it).map { it.value } }
        .associate { seq ->
            val (a, b, c) = seq.toList()
            a to (b to c)
        }

    fun part1(input: List<String>): Int {
        val instructions = input.first()
        val network = createNetwork(input)

        var current = "AAA"

        0.rangeTo(Int.MAX_VALUE).forEach {
            current = if ('L' == instructions[it % instructions.length]) network[current]!!.first else network[current]!!.second
            if ("ZZZ" == current) {
                return it + 1
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")

    check(part1(testInput) == 6)
    check(part2(testInput) == 0)

    val input = readInput("Day08")
    part1(input).println()
    part2(input).println()
}