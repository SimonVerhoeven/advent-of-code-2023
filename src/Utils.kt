import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.math.absoluteValue

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

fun readInputText(name: String) = Path("src/$name.txt").readText()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

fun Int.pow(n: Int): Int = when {
    n == 0 -> 1
    n % 2 == 0 -> (this * this).pow(n / 2)
    else -> this * pow(n - 1)
}

fun Boolean.toInt(): Int {
    return if (this) 1 else 0
}

fun greatestCommonDenominator(a: Long, b: Long): Long {
    if (b == 0L) return a
    return greatestCommonDenominator(b, a % b)
}

fun leastCommonMultiple(a: Long, b: Long): Long {
    return a / greatestCommonDenominator(a, b) * b
}

fun Iterable<Long>.leastCommonMultiple() = reduce { acc, i -> leastCommonMultiple(acc, i) }

fun List<String>.toCharMatrix() = Array(size) { idx -> get(idx).toCharArray() }

infix fun String.diff(other: String): Int = indices.count { this[it] != other[it] } + (length - other.length).absoluteValue

fun List<String>.columnToString(column: Int): String = this.map { it[column] }.joinToString("")

fun parseInput(input: List<String>): List<List<String>> = input.joinToString("\n").split("\n\n").map { it.lines() }

fun createRanges(from: Int, to: Int): List<Pair<Int, Int>> = (from downTo 0).zip(from + 1..to)