import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

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