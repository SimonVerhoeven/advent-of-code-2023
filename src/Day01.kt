fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf { it -> it.mapNotNull { it.digitToIntOrNull() }.let { 10 * it.first() + it.last() } }
    }

    fun replaceNumbers(input: String): String {
        return input.replace("one", "o1e")
            .replace("two", "t2o")
            .replace("three", "t3e")
            .replace("four", "f4r")
            .replace("five", "f5v")
            .replace("six", "s6x")
            .replace("seven", "s7n")
            .replace("eight", "e8t")
            .replace("nine", "n9e")
    }

    fun part2(input: List<String>): Int {
        return input.map { replaceNumbers(it).filter { it.isDigit() } }.sumOf { (it.first() + "" + it.last()).toInt() }
    }
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
