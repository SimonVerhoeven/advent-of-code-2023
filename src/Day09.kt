fun main() {
    fun predictLast(history: List<Int>): Int {
        return if (history.all { it == 0 }) return 0 else (history.last() + predictLast(history.zipWithNext { a, b -> b - a }))
    }

    fun part1(input: List<String>): Int {
        return input.map { it.split(" ").map { it.toInt() } }
            .sumOf { predictLast(it) }
    }

    fun part2(input: List<String>): Int {

        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")

    check(part1(testInput) == 114)
    check(part2(testInput) == 0)

    val input = readInput("Day09")
    part1(input).println()
    part2(input).println()
}