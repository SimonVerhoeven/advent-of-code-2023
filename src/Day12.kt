fun String.toGroups() = split(',').map { it.toInt() }

fun calculateArrangement(
    pattern: String,
    groups: List<Int>,
    rowIndex: Int = 0,
    columnIndex: Int = 0,
    cache: MutableMap<Pair<Int, Int>, Long> = mutableMapOf()
): Long {
    val damagedIndex = pattern.drop(rowIndex).indexOf('#')
    if (columnIndex == groups.size) {
        return if (damagedIndex == -1) 1L else 0L
    } else {
        return pattern.drop(rowIndex)
            .windowed(groups[columnIndex], 1)
            .withIndex()
            .filter { (i, window) ->
                if (window.any { it == '.' }) return@filter false
                if (damagedIndex > -1 && i > damagedIndex) return@filter false

                val leftIndex = rowIndex + i
                val leftmostOperational = leftIndex == 0 || pattern[leftIndex - 1] == '.' || pattern[leftIndex - 1] == '?'
                val rightIndex = rowIndex + window.length + i
                val rightmostOperational = (rightIndex == pattern.length) || pattern[rightIndex] == '.' || pattern[rightIndex] == '?'
                leftmostOperational && rightmostOperational
            }
            .sumOf { (i, window) ->
                val newRowIndex = rowIndex + window.length + i + 1
                val newColumnIndex = columnIndex + 1
                cache.getOrPut(newRowIndex to newColumnIndex) {
                    calculateArrangement(
                        pattern,
                        groups,
                        newRowIndex,
                        newColumnIndex,
                        cache
                    )
                }
            }
    }
}

fun main() {
    fun part1(input: Pair<List<String>, List<List<Int>>>): Long {
        return input.first.zip(input.second).sumOf { (row, column) -> calculateArrangement(row, column) }
    }

    fun part2(input: Pair<List<String>, List<List<Int>>>): Long {
        val expandedRows = input.first.map { "$it?".repeat(5).dropLast(1) }
        val expandedGroups = input.second.map { group ->
            group.joinToString(separator = ",", postfix = ",").repeat(5).dropLast(1).split(',').map { it.toInt() }
        }
        return expandedRows.zip(expandedGroups).sumOf { (row, column) -> calculateArrangement(row, column) }
    }

    fun parseInput(input: List<String>): Pair<List<String>, List<List<Int>>> {
        val rows = mutableListOf<String>()
        val groups = mutableListOf<List<Int>>()
        input.forEach { line ->
                val (rowString, groupString) = line.split(' ')
                rows += rowString
                groups.add(groupString.toGroups())
            }
        return rows to groups
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    val parsedTestInput = parseInput(testInput)
    check(part1(parsedTestInput) == 21L)
    check(part2(parsedTestInput) == 525152L)

    val input = readInput("Day12")
    val parsedInput = parseInput(input)
    part1(parsedInput).println()
    part2(parsedInput).println()
}
