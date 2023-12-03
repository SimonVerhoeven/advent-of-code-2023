fun main() {
    fun part1(input: List<String>): Int {
        return 0

    }

    fun part2(input: List<String>): Int {
        return 0
    }

    fun toDigitMap(input: List<String>): Array<CharArray> {
        return input.map{ it.toCharArray()}.toTypedArray();
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    toDigitMap(testInput).forEach { it.concatToString().println() }
//    check(part1(testInput) == 142)
//
//    val input = readInput("Day03")
//    part1(input).println()
//    part2(input).println()
}
