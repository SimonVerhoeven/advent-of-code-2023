fun main() {
    fun parseInput(input: List<String>): List<Int> {
        return input.map { line ->
            val (winningNumbers, scratchedNumbers) = line.substringAfter(": ").split(" | ").map {
                numbers -> numbers.split(" ").filter { it.isNotEmpty() }.mapTo(hashSetOf()) { it.toInt() }
            }

            scratchedNumbers.intersect(winningNumbers).size
        }
    }

    fun part1(input: List<Int>): Int {
        return input.sumOf { number -> if (0 == number) 0 else 2.pow(number-1)}
    }

//    fun part2(input: List<String>): Int {
//    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("Day04_test"))
    check(part1(testInput) == 13)

    val input = readInput("Day04")
    part1(parseInput(input)).println()
//    part2(input).println()
}
