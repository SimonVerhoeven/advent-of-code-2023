fun main() {
    val colours = mapOf("red" to 12, "green" to 13, "blue" to 14)

    fun part1(games: List<List<Map<String, Int>>>): String {
        return games.withIndex().filter {
            (_, game) -> game.all { bucket -> bucket.all { (colour, cubes) -> colours.getValue(colour) >= cubes } }
        }.sumOf { (index) -> index + 1 }.toString()
    }

    fun mapCubes(lines: List<String>): List<List<Map<String, Int>>> {
        return lines.map { line ->
            line.substringAfter(": ").split("; ").map { colourPortion ->
                colourPortion.split(", ")
                    .associate { colourAmount ->
                        val (amount, colour) = colourAmount.split(" ")
                        colour to amount.toInt()
                    }.withDefault { 0 }
            }
        }
    }

    fun part2(games: List<List<Map<String, Int>>>): String {
        return "0";
//        return input.map { replaceNumbers(it).filter { it.isDigit() } }.sumOf { (it.first() + "" + it.last()).toInt() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    part1(mapCubes(testInput)).println()
    val input = readInput("Day02")
    part1(mapCubes(input)).println()
    part2(mapCubes(input)).println()
}
