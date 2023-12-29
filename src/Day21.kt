typealias GardenMap = Array<CharArray>
typealias Step = Pair<Coordinate, Int>

fun GardenMap.findStartingPoint(): Coordinate = this.mapIndexedNotNull { y, row ->
    if ('S' in row) Coordinate(row.indexOf('S'), y) else null
}.first()

fun GardenMap.countSteps(targetSteps: Int): Map<Coordinate, Int> {
    val self = this
    return buildMap {
        val startingPoint = findStartingPoint();
        val queue = ArrayDeque<Step>()
        queue.add(startingPoint to 0)

        while (queue.isNotEmpty()) {
            queue.removeFirst().let { (location, distance) ->
                if (location !in this && distance <= targetSteps) {
                    this[location] = distance
                    queue.addAll(
                        location.cardinalNeighbours()
                            // prevent backtracking...
                            .filter { it !in this }
                            .filter { withinArray(it) }
                            .filter { self[it] != '#' }
                            .map { it to distance + 1 }
                    )
                }
            }
        }
    }
}

fun main() {
    fun part1(input: GardenMap, targetSteps: Int): Int {
        return input.countSteps(targetSteps).values.count { it % 2 == 0 }
    }

    fun part2(input: GardenMap, targetSteps: Int): Long {
        return 0L
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day21_test").toCharMatrix()
    check(part1(testInput, 6) == 16)
    check(part2(testInput, 6) == 0L)

    val input = readInput("Day21").toCharMatrix()
    part1(input, 64).println()
    part2(input, 26501365).println()
}
