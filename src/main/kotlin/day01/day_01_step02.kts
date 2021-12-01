import java.io.File

// https://adventofcode.com/2021/day/1#part2
fun day01_step02(input: String): Int {
    val depths = input
        .split("\n")
        .map { it.trim() }
        .map { it.toInt() }

    return depths
        .zipWithNext { a, b -> a + b }
        .zip(depths.drop(2)) { a, b -> a + b }
        .zipWithNext { a, b -> if (b > a) 1 else 0 }
        .sum()
}

fun test_day01_step02() {
    val testInput = """
        199
        200
        208
        210
        200
        207
        240
        269
        260
        263
    """.trimIndent()

    val actual = day01_step02(testInput)

    println("Test result: ${actual}")
}

fun run_day01_step02() {
    val input = File("input.txt").readText()
    val actual = day01_step02(input)

    println("Result: ${actual}")
}

test_day01_step02()
run_day01_step02()
