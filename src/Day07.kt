fun main() {
    fun part1(input: List<String>): Int {
        return input
            .asSequence()
            .map { it.split(" ") }
            .map { (cards, bid) -> Hand(cards, bid.toInt()) }
            .map { hand ->
                hand to hand.cards
                    .groupingBy { it }
                    .eachCount()
                    .values
                    .sorted()
                    .toHandType()
            }.sortedWith(
                compareByDescending<Pair<Hand, HandType?>> { (_, handType) -> handType }
                    .thenBy { (hand, _) ->
                        hand.cards
                            .replace('T', 'V')
                            .replace('J', 'W')
                            .replace('Q', 'X')
                            .replace('K', 'Y')
                            .replace('A', 'Z')
            }).withIndex().sumOf { (idx, pair) -> (idx+1) * pair.first.bid }


    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 6440)
    check(part2(testInput) == 0)

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}

internal enum class HandType(val distribution: List<Int>) {
    FiveOfAKind(listOf(5)),
    FourOfAKind(listOf(1, 4)),
    FullHouse(listOf(2, 3)),
    ThreeOfAKind(listOf(1, 1, 3)),
    TwoPair(listOf(1, 2, 2)),
    OnePair(listOf(1, 1, 1, 2)),
    HighCard(listOf(1, 1, 1, 1, 1));
}

internal fun List<Int>.toHandType() = HandType.entries.firstOrNull { this == it.distribution }

internal data class Hand(val cards: String, val bid: Int)