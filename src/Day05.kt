fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("Day05_test"))
    check(part1(testInput) == 35)

    val input = parseInput(readInput("Day05"))
    part1(input).println()
    part2(input).println()
}
