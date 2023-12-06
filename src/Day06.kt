fun main() {

    fun getTime(time: Long, holdingTime: Long): Long {
        return (time - holdingTime) * holdingTime
    }

    fun part1(input: List<String>): Long {
        val times = input[0].removePrefix("Time:").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
        val distances = input[1].removePrefix("Distance:").split(" ").filter { it.isNotBlank() }.map { it.toLong() }

        return times.zip(distances).fold(1) { acc, (time, distance) -> acc * (0..time).sumOf { (getTime(time, it) > distance).toInt() }.toLong() }
    }

    fun part2(input: List<String>): Long {
        val time = input[0].removePrefix("Time:").replace(" ", "").toLong()
        val distance = input[1].removePrefix("Distance:").replace(" ", "").toLong()

        return (0..time).sumOf { (getTime(time, it) > distance).toInt() }.toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 288L)
    check(part2(testInput) == 71503L)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
