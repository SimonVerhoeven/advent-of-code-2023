fun main() {
    fun part1(input: Pair<Map<Coordinate, Char>, List<PartNumber>>): Int {
        val symbols = input.first
        val partNumbers = input.second

        return partNumbers.filter {
            partNumber -> partNumber.coordinates.any {
                coordinate -> listOf(
                        Coordinate(coordinate.x - 1, coordinate.y - 1), Coordinate(coordinate.x, coordinate.y - 1), Coordinate(coordinate.x + 1, coordinate.y - 1),
                        Coordinate(coordinate.x - 1, coordinate.y), Coordinate(coordinate.x + 1, coordinate.y),
                        Coordinate(coordinate.x - 1, coordinate.y + 1), Coordinate(coordinate.x, coordinate.y + 1), Coordinate(coordinate.x + 1, coordinate.y + 1)
                    ).any { symbols.containsKey( it )}
            }
        }.sumOf { it.number }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    fun parseInput(input: List<String>): Pair<Map<Coordinate, Char>, List<PartNumber>> {
        val symbols = mutableMapOf<Coordinate, Char>()
        val partNumbers = mutableListOf<PartNumber>()
        input.forEachIndexed { yIndex, line ->
            var partNumber = ""
            val partNumberCoordinates = mutableListOf<Coordinate>()
            line.forEachIndexed { xIndex, character ->
                val coordinate = Coordinate(xIndex, yIndex)
                if (character.isDigit()) {
                    partNumber += character
                    partNumberCoordinates.add(coordinate)
                } else {
                    if ('.' != character) {
                        symbols[coordinate] = character
                    }
                    if (partNumber.isNotEmpty()) {
                        partNumbers.add(PartNumber(partNumber.toInt(), ArrayList(partNumberCoordinates)))
                        partNumber = ""
                        partNumberCoordinates.clear()
                    }
                }

            }

            if (partNumber.isNotEmpty()) {
                partNumbers.add(PartNumber(partNumber.toInt(), ArrayList(partNumberCoordinates)))
            }
        }
        return Pair(symbols, partNumbers)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = parseInput(readInput("Day03_test"))
    part1(testInput).println()

    part1(parseInput(readInput("Day03"))).println()
//    part2(input).println()
}

data class Coordinate(val x: Int, val y: Int)
data class PartNumber(val number: Int, val coordinates: List<Coordinate>)
