typealias GardenMap = Array<CharArray>
fun GardenMap.findStartingPoint(): Coordinate = this.mapIndexedNotNull { y, row ->
    if ('S' in row) Coordinate(row.indexOf('S'), y) else null
}.first()

fun GardenMap.determineRoutes(targetSteps: Int) {
    val startingPoint = this.findStartingPoint();
    
}

fun main() {
    fun part1(input: List<String>, targetSteps: Int): Int {
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day21_test")
    check(part1(testInput, 6) == 0)
    check(part2(testInput) == 0)

    val input = readInput("Day21")
    part1(input, 64).println()
    part2(input).println()
}
