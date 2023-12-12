import kotlin.math.absoluteValue

val NORTH = -1 to 0
val EAST = 0 to 1
val SOUTH = 1 to 0
val WEST = 0 to -1

fun main() {

    fun mapInput(input: List<String>): Pair<Pair<Int, Int>, Map<Pair<Int, Int>, List<Pair<Int, Int>>>> {
        lateinit var startingPoint: Pair<Int, Int>

        val grid = input.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, character ->
                (y to x) to when (character) {
                    'L' -> listOf(NORTH, EAST)
                    '|' -> listOf(NORTH, SOUTH)
                    'J' -> listOf(NORTH, WEST)
                    'F' -> listOf(EAST, SOUTH)
                    '-' -> listOf(EAST, WEST)
                    '7' -> listOf(SOUTH, WEST)
                    'S' -> listOf(NORTH, EAST, SOUTH, WEST).also { startingPoint = y to x }
                    else -> emptyList()
                }.map { (y2, x2) -> y2 + y to x2 + x }
            }
        }.toMap()
        return Pair(startingPoint, grid)
    }

    fun mapPoints(input: List<String>): List<Pair<Int, Int>> {
        val mappedInput = mapInput(input)
        val startingPoint = mappedInput.first
        val grid = mappedInput.second

        val firstMove = grid.getValue(startingPoint).first { from -> grid.getValue(from).any(startingPoint::equals) }
        return generateSequence(startingPoint to firstMove) { (from, to) ->
            when (to) { startingPoint -> null else -> to to grid.getValue(to).minus(from).first() }
        }.map { it.first }.toList()
    }

    fun part1(points: List<Pair<Int, Int>>): Int {
        return points.size / 2
    }

    fun part2(points: List<Pair<Int, Int>>): Int {
        return points.plus(points.first())
            .zipWithNext { (y1, x1), (_, x2) -> (x2 - x1) * y1 }
            .sum().absoluteValue - part1(points) + 1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    val testPoints = mapPoints(testInput)
    check(part1(testPoints) == 8)
    check(part2(testPoints) == 1)

    val input = readInput("Day10")
    val points = mapPoints(input)
    part1(points).println()
    part2(points).println()
}