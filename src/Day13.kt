fun main() {

    fun findHorizontalMirror(pattern: List<String>, desiredTotal: Int): Int? {
        return (0 until pattern.lastIndex).firstNotNullOfOrNull { start ->
            if (createRanges(start, pattern.lastIndex)
                    .sumOf { (up, down) -> pattern[up] diff pattern[down] } == desiredTotal
            ) (start + 1) * 100
            else null
        }
    }

    fun findVerticalMirror(pattern: List<String>, desiredTotal: Int): Int? {
        return (0 until pattern.first().lastIndex).firstNotNullOfOrNull { start ->
            if (createRanges(start, pattern.first().lastIndex)
                    .sumOf { (left, right) ->pattern.columnToString(left) diff pattern.columnToString(right) } == desiredTotal
            ) start + 1
            else null
        }
    }

    fun determineMirror(pattern: List<String>, goal: Int): Int {
        return findHorizontalMirror(pattern, goal) ?: findVerticalMirror(pattern, goal) ?: throw IllegalArgumentException("Unmirrorable pattern")
    }

    fun part1(patterns: List<List<String>>): Int {
        return patterns.sumOf{ determineMirror(it, 0) }
    }

    fun part2(patterns: List<List<String>>): Int {
        return patterns.sumOf{ determineMirror(it, 1) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    val testPatterns = parseInput(testInput)
    check(part1(testPatterns) == 405)
    check(part2(testPatterns) == 400)

    val input = readInput("Day13")
    val patterns = parseInput(input)
    part1(patterns).println()
    part2(patterns).println()
}
