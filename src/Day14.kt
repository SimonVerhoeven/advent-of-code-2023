typealias Platform = Array<CharArray>

fun Platform.isValid(coordinate: Coordinate): Boolean {
    return coordinate.y in this.indices && coordinate.x in this[coordinate.y].indices
}

fun Platform.switchPosition(source: Coordinate, destination: Coordinate) {
    val charToSwap = this[source.y][source.x]
    this[source.y][source.x] = this[destination.y][destination.x]
    this[destination.y][destination.x] = charToSwap
}

fun main() {
//    fun Platform.tilt(direction: CardinalDirection) {
//
//    }
//
//    fun Platform.cycle() {
//        tilt(CardinalDirection.NORTH)
//        tilt(CardinalDirection.EAST)
//        tilt(CardinalDirection.SOUTH)
//        tilt(CardinalDirection.WEST)
//    }

    fun Platform.score(): Int =
        mapIndexed { y, row -> row.sumOf { c ->if (c == 'O') size - y else 0 } }.sum()

    fun part1(platform: Platform): Int {
        return 0;
//        return platform.tilt(CardinalDirection.NORTH).score()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
//    check(part1(testInput) == 0)
//    check(part2(testInput) == 0)

    val input = readInput("Day14")
//    part1(input).println()
//    part2(input).println()
}
