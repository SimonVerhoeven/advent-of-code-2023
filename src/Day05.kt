fun main() {

    fun createConversionMaps(input: List<String>): List<Map<LongRange, LongRange>> {
        val conversionMaps = input
                .drop(2)
                .joinToString("\n")
                .split("\n\n")
                .map { segment ->
                    segment.lines().drop(1).associate {
                        it.split(" ").map { it.toLong() }.let { (dest, source, length) ->
                            source..(source + length) to dest..(dest + length)
                        }
                    }
                }
        return conversionMaps
    }

    fun part1(input: List<String>): Long {
        val seeds = input.first().substringAfter(" ").split(" ").map { it.toLong() }
        val conversionMaps = createConversionMaps(input)

        return seeds.minOf { seed ->
            conversionMaps.fold(seed) { value, map ->
                map.entries.firstOrNull { value in it.key }?.let { (source, dest) -> dest.first + (value - source.first) } ?: value
            }
        }
    }

    fun part2(input: List<String>): Long {
       return 0;
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 35L)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
