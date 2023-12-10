fun main() {
    fun predictLast(history: List<Int>): Int {
        return if (history.all { it == 0 }) return 0 else (history.last() + predictLast(history.zipWithNext { a, b -> b - a }))
    }

    fun part1(readings: List<List<Int>>): Int {
        return readings.sumOf { predictLast(it) }
    }

    fun predictFirst(history: List<Int>): Int {
        return if (history.all { it == 0 }) return 0 else (history.first() - predictFirst(history.zipWithNext { a, b -> b - a }))
    }

    fun part2(readings: List<List<Int>>): Int {
        return readings.sumOf { predictFirst(it) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    val testReadings = testInput.map { it.split(" ").map { it.toInt() } }
    check(part1(testReadings) == 114)
    check(part2(testReadings) == 2)

    val input = readInput("Day09")
    val readings = input.map { it.split(" ").map { it.toInt() } }
    part1(readings).println()
    part2(readings).println()
}