fun main() {
    data class Box(val label: String, val value: Long)

    fun String.hash() = this.fold(0) { carry, character -> (carry + character.code) * 17 % 256 }

    fun part1(input: List<String>): Int {
        return input.sumOf { it.hash() }
    }

    fun part2(input: List<String>): Long {
        val boxes = Array<MutableList<Box>>(257) { mutableListOf() }
        input.forEach { operation ->
            if ('-' == operation.last()) {
                // Remove lens if present
                val label = operation.dropLast(1)
                boxes[label.hash()].removeIf { it.label == label }
            } else {
                val (label, value) = operation.split("=", limit = 2)
                with(boxes[label.hash()]) {
                    val labelIndex = indexOfFirst { it.label == label }
                    if (labelIndex > -1) {
                        set(labelIndex, Box(label, value.toLong()))
                    } else {
                        add(Box(label, value.toLong()))
                    }
                }
            }
        }
        return boxes.withIndex().sumOf { (boxPosition, contents) ->
            contents.withIndex().sumOf { (index, entry) ->
                ((boxPosition + 1) * (index + 1) * entry.value)
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day15_test").first().split(",")
    check(part1(testInput) == 1320)
    check(part2(testInput) == 145L)

    val input = readInput("Day15").first().split(",")
    part1(input).println()
    part2(input).println()
}
