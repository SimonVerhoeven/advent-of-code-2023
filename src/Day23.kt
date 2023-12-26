fun main() {

    val slopes = mapOf(
        '^' to Direction.UP,
        '>' to Direction.RIGHT,
        'v' to Direction.DOWN,
        '<' to Direction.LEFT,
    )

    fun getStartAndAnd(input: Array<CharArray>): Pair<Coordinate, Coordinate> {
        val start = Coordinate(input.first().indexOfFirst { it == '.' }, 0)
        val end = Coordinate(input.last().indexOfFirst { it == '.' }, input.lastIndex)
        return Pair(start, end)
    }

    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day23_test")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)

    val input = readInput("Day23")
    part1(input).println()
    part2(input).println()
}
