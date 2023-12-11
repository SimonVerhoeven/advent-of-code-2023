import kotlin.math.abs

fun main() {

    data class Cell(val x: Int, val y: Int)

    fun Pair<Cell, Cell>.shortestPath(rowsEmptySpace: List<Int>, colEmptySpace: List<Int>, expansionFactor: Long): Long {
        val stepLine = abs(this.first.x - this.second.x)
        val stepCol = abs(this.first.y - this.second.y)

        val x = rowsEmptySpace.count { it in this.first.x..this.second.x || it in this.second.x..this.first.x }
        val y = colEmptySpace.count { it in this.first.y..this.second.y || it in this.second.y..this.first.y }

        val stepsByEmptySpace = expansionFactor - 1

        return stepLine + stepCol + x * stepsByEmptySpace + y * stepsByEmptySpace
    }

    fun determine(input: List<String>, expansionFactor: Long): Long {
        val rowsWithEmptySpace = MutableList(input.size - 1) { it }
        val colsWithEmptySpace = MutableList(input[0].length - 1) { it }

        return input.flatMapIndexed { index, line ->
            val galaxies = mutableListOf<Cell>()

            for ((rowId, character) in line.withIndex()) {
                if (character == '#') {
                    galaxies.add(Cell(index, rowId))
                    rowsWithEmptySpace.remove(index)
                    colsWithEmptySpace.remove(rowId)
                }
            }

            galaxies
        }.toList().let {
            val pairs = mutableListOf<Pair<Cell, Cell>>()

            for (idx in it.indices) {
                for (nextIdx in (idx + 1) until it.size) {
                    pairs.add(Pair(it[idx], it[nextIdx]))
                }
            }

            pairs
        }.sumOf { it.shortestPath(rowsWithEmptySpace, colsWithEmptySpace, expansionFactor) }
    }

    fun part1(input: List<String>): Long {
        return determine(input, 2L)
    }

    fun part2(input: List<String>): Long {
        return determine(input,  1_000_000L)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 374L)
    check(part2(testInput) == 82000210L)

    val input = readInput("Day11")
    part1(input).println()
    part2(input).println()
}