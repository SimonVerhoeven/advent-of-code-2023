// Slow, brute method

fun main() {
    data class Mapping(val range: LongRange, val offset: Long)

    fun createConversionMaps(input: List<String>): List<List<Mapping>> {
        val iterator = input.drop(2).iterator()

        return buildList {
            while (iterator.hasNext()) {
                check(iterator.next().endsWith("map:"))
                var line = iterator.next()
                val mappings = buildList {
                    while (line.isNotBlank()) {
                        val (destinationRangeStart, sourceRangeStart, rangeLength) = line.split(" ").filter { it.isNotBlank() }.map { it.toLong() }
                        val offset = destinationRangeStart - sourceRangeStart
                        add(Mapping(sourceRangeStart..<(sourceRangeStart + rangeLength), offset))
                        line = (if (iterator.hasNext()) iterator.next() else "")
                    }
                }
                add(mappings)
            }
        }
    }

    fun part1(seedLine: String, conversionMaps: List<List<Mapping>>): Long {
        return seedLine.substringAfter(" ").split(" ").map { it.toLong() }.minOf { seed ->
            conversionMaps.fold(seed) { acc, mappings ->
                val offset = mappings.find { acc in it.range }?.offset ?: 0
                acc + offset
            }
        }
    }

    fun part2(seedLine: String, conversionMaps: List<List<Mapping>>): Long {
        val seeds = seedLine.removePrefix("seeds: ").split(" ").filter { it.isNotBlank() }.map { it.toLong() }
        return seeds
            .asSequence()
            .windowed(2, 2)
            .flatMap { (start, length) -> start ..< (start + length) }
            .minOf { seed ->
                conversionMaps.fold(seed) { acc, mappings ->
                    val offset = mappings.find { acc in it.range }?.offset ?: 0
                    acc + offset
                }
            }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    val testSeedLine = testInput.first()
    val testConversionMappings = createConversionMaps(testInput)
    check(part1(testSeedLine, testConversionMappings) == 35L)
    check(part2(testSeedLine, testConversionMappings) == 46L)

    val input = readInput("Day05")
    val seedLine = input.first()
    val conversionMappings = createConversionMaps(input)

    part1(seedLine, conversionMappings).println()
    part2(seedLine, conversionMappings).println()
}
