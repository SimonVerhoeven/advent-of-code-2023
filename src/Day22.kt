fun main() {
    fun part1(input: List<String>): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day22_test")
    check(part1(testInput) == 0)
    check(part2(testInput) == 0)

    val input = readInput("Day22")
    part1(input).println()
    part2(input).println()
}

private data class Brick(private val a: Point3D, private val b: Point3D)