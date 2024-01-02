fun main() {
    val colouredCubeStock = mapOf("red" to 12, "green" to 13, "blue" to 14)

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

    fun part1(games: List<List<Map<String, Int>>>): Int {
        return games.withIndex().filter {
            (_, game) -> game.all { bucket -> bucket.all { (colour, cubes) -> colouredCubeStock.getValue(colour) >= cubes } }
        }.sumOf { (index) -> index + 1 }
    }

    fun part2(games: List<List<Map<String, Int>>>): Int {
        return games.sumOf {
            game -> colouredCubeStock.keys.map {
                game.maxOf {
                    bucket -> bucket.getValue(it)
                }
            }.reduce { acc, curr -> acc * curr }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(mapCubes(testInput)) == 8)
    check(part2(mapCubes(testInput)) == 2286)

    val input = readInput("Day02")
    part1(mapCubes(input)).println()
    part2(mapCubes(input)).println()
}
