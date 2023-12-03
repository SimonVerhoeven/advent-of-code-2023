fun main() {
    fun part1(input: List<String>): Int {
        return 0

    }

    fun part2(input: List<String>): Int {
        return 0
    }

    fun parseInput(input: List<String>): Pair<List<Symbol>, List<PartNumber>> {
        val symbols = mutableListOf<Symbol>()
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
                    symbols.add(Symbol(character, coordinate))
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
    val testInput = readInput("Day03_test")
    val test = parseInput(testInput);
    val test2 = parseInput(testInput);

//    check(part1(testInput) == 142)
//
//    val input = readInput("Day03")
//    part1(input).println()
//    part2(input).println()
}

data class Coordinate(val x: Int, val y: Int)
data class Symbol(val character: Char, val coordinate: Coordinate)
data class PartNumber(val number: Int, val coordinates: List<Coordinate>)
