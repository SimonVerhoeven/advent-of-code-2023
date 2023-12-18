import java.util.*

fun main() {
    data class CityBlock(val coordinate: Coordinate, val direction: Direction, val count: Int)
    fun CityBlock.moveTo(input: Array<List<Int>>, direction: Direction): CityBlock? {

        val isOppositeDirection = when (direction) {
            Direction.UP -> this.direction == Direction.DOWN
            Direction.DOWN -> this.direction == Direction.UP
            Direction.RIGHT -> this.direction == Direction.LEFT
            Direction.LEFT -> this.direction == Direction.RIGHT
        }

        if (isOppositeDirection) return null

        val (newX, newY) = when (direction) {
            Direction.UP -> Pair(coordinate.x - 1, coordinate.y)
            Direction.DOWN -> Pair(coordinate.x + 1, coordinate.y)
            Direction.RIGHT -> Pair(coordinate.x, coordinate.y + 1)
            Direction.LEFT -> Pair(coordinate.x, coordinate.y - 1)
        }

        if (newX !in input.indices || newY !in input[newX].indices) return null

        val newDirectionCount = if (direction == this.direction) count + 1 else 1

        return CityBlock(Coordinate(newX, newY), direction, newDirectionCount)
    }

    fun findMinimalHeathLossPath(input: Array<List<Int>>, minConsecutiveBlocks: Int = 0, maxConsecutiveBlocks: Int = Int.MAX_VALUE): Int {
        val losses = mutableMapOf<CityBlock, Int>().withDefault { Int.MAX_VALUE }
        val predecessors = mutableMapOf<CityBlock, CityBlock>()
        val queue = PriorityQueue<CityBlock>( compareBy { losses.getValue(it) })

        listOf(
            CityBlock(Coordinate(0, 0), Direction.RIGHT, 1),
            CityBlock(Coordinate(0, 0), Direction.DOWN, 1)
        ).forEach {
            losses[it] = 0
            queue += it
        }

        while (queue.isNotEmpty()) {
            val current = queue.remove()

            val neighbours = buildList {
                if (current.count < minConsecutiveBlocks) {
                    current.moveTo(input, current.direction)?.also { add(it) }
                } else {
                    Direction.entries.forEach { direction ->
                        current.moveTo(input, direction)
                            ?.takeIf { it.count <= maxConsecutiveBlocks }
                            ?.also { add(it) }
                    }
                }
            }

            neighbours.forEach { neighbour ->
                val alternative = losses.getValue(current) + input[neighbour.coordinate.x][neighbour.coordinate.y]
                if (alternative < losses.getValue(neighbour)) {
                    losses[neighbour] = alternative
                    predecessors[neighbour] = current
                    queue.add(neighbour)
                }
            }
        }

        return losses.filterKeys { it.coordinate.x == input.size - 1 && it.coordinate.y == input[input.size - 1].size - 1 }.minOf { it.value }
    }


    fun part1(input: Array<List<Int>>) = findMinimalHeathLossPath(input, maxConsecutiveBlocks = 3)

    fun part2(input: Array<List<Int>>) = findMinimalHeathLossPath(input, minConsecutiveBlocks = 4, maxConsecutiveBlocks = 10)

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day17_test").toIntMatrix()
    part2(testInput).println()
    check(part2(testInput) == 71)

    val input = readInput("Day17").toIntMatrix()
    part1(input).println()
    part2(input).println()
}
