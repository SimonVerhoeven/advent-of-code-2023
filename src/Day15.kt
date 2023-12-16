fun main() {
    fun String.hash() = this.fold(0) { carry, character -> (carry + character.code) * 17 % 256 }

    fun part1(input: List<String>): Int {
        return input.first().split(",").sumOf { it.hash() }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day15_test")
    check(part1(testInput) == 1320)
    check(part2(testInput) == 0)

    val input = readInput("Day15")
    part1(input).println()
    part2(input).println()
}
