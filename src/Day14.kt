typealias Platform = Array<CharArray>

fun Platform.orient(direction: CardinalDirection): List<Coordinate>  {
    when (direction) {
        CardinalDirection.NORTH -> {
            return this.indices.flatMap { y ->
                this.first().indices.map { x ->
                    Coordinate(x, y)
                }
            }
        }
        CardinalDirection.EAST -> {
            return this.first().indices.reversed().flatMap { x ->
                this.indices.map { y ->
                    Coordinate(x, y)
                }
            }
        }
        CardinalDirection.SOUTH -> {
            return this.indices.reversed().flatMap { y ->
                this.first().indices.map { x ->
                    Coordinate(x, y)
                }
            }
        }
        CardinalDirection.WEST -> {
            return this.first().indices.flatMap { x ->
                this.indices.map { y ->
                    Coordinate(x, y)
                }
            }
        }
        else -> error("Invalid CardinalDirection")
    }
}

fun Platform.tilt(direction: CardinalDirection, blockToMove: Char) {
     this.orient(direction)
         .filter { this[it.y][it.x] == blockToMove}
         .forEach{ shift(it, direction) }
}

fun Platform.isValid(coordinate: Coordinate): Boolean {
    return coordinate.y in this.indices && coordinate.x in this[coordinate.y].indices
}

fun Platform.switchPosition(source: Coordinate, destination: Coordinate) {
    val charToSwap = this[source.y][source.x]
    this[source.y][source.x] = this[destination.y][destination.x]
    this[destination.y][destination.x] = charToSwap
}

fun getPosition(coordinate: Coordinate, direction: CardinalDirection): Coordinate {
    return when (direction) {
        CardinalDirection.NORTH -> Coordinate(coordinate.x, coordinate.y - 1)
        CardinalDirection.EAST -> Coordinate(coordinate.x + 1, coordinate.y)
        CardinalDirection.SOUTH -> Coordinate(coordinate.x, coordinate.y + 1)
        CardinalDirection.WEST -> Coordinate(coordinate.x + 1, coordinate.y)
        else -> error("Invalid CardinalDirection")
    }
}

fun Platform.shift(origin: Coordinate, direction: CardinalDirection) {
    var current = origin
    while (isValid(getPosition(current, direction)) && this[getPosition(current, direction).y][getPosition(current, direction).x] == '.') {
        switchPosition(current, getPosition(current, direction))
        current = getPosition(current, direction)
    }
}

fun main() {

    fun Platform.score(): Int =
        mapIndexed { y, row -> row.sumOf { c ->if (c == 'O') size - y else 0 } }.sum()

    fun part1(platform: Platform): Int {
        platform.tilt(CardinalDirection.NORTH, 'O')
        return platform.score()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test").toCharMatrix()
    check(part1(testInput) == 136)
//    check(part2(testInput) == 0)

    val input = readInput("Day14").toCharMatrix()
    part1(input).println()
//    part2(input).println()
}
