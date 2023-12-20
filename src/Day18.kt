import kotlin.math.absoluteValue

typealias DigPlan = List<Triple<Direction, Int, Int>>

fun List<Pair<Direction, Int>>.calculate() = runningFold(Coordinate(0, 0)) { coordinate, (direction, amount) -> calculateAmount(coordinate, direction, amount) }
    .zipWithNext { (y1, x1), (_, x2) -> (x2 - x1) * y1.toLong() }
    .sum().absoluteValue + sumOf { it.second } / 2 + 1

fun calculateAmount(coordinate: Coordinate, direction: Direction, amount: Int): Coordinate {
    return when (direction) {
        Direction.UP -> Coordinate(coordinate.x, coordinate.y + 1 * amount)
        Direction.DOWN -> Coordinate(coordinate.x, coordinate.y - 1 * amount)
        Direction.LEFT -> Coordinate(coordinate.x - 1 * amount, coordinate.y)
        Direction.RIGHT -> Coordinate(coordinate.x + 1 * amount, coordinate.y)
    }
}

fun main() {

    fun toDirection(direction: String): Direction {
        return when (direction) {
            "U" -> Direction.UP
            "D" -> Direction.DOWN
            "L" -> Direction.LEFT
            "R" -> Direction.RIGHT
            else -> error("Invalid direction")
        }
    }

    fun createDigPlan(input: List<String>): DigPlan {
        return input.map { line ->
            line.split(" ").let { (direction, meters, colour) ->
                Triple(toDirection(direction), meters.toInt(), colour.substring(2..7).toInt(16))
            }
        }
    }

    fun part1(digPlan: DigPlan): Long {
        return digPlan.map { (direction, meters, _) -> direction to meters }.calculate()
    }

    fun part2(digPlan: DigPlan): Long {
        return 0L
    }

    // test if implementation meets criteria from the description, like:
    val testInput = createDigPlan(readInput("Day18_test"))
    check(part1(testInput) == 62L)
//    check(part2(testInput) == 952408144115L)

    val input = createDigPlan(readInput("Day18"))
    part1(input).println()
    part2(input).println()
}
