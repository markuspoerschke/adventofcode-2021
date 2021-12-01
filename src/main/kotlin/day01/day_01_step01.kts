import java.io.File

// https://adventofcode.com/2021/day/1#part1
fun day01_step01(input: String): Int {
    val depths = input
        .split("\n")
        .map { it.trim() }
        .map { it.toInt() }

    var depthIncrease = 0
    var previousDepth = depths.first()
    for (depth in depths.drop(1)) {
        if (previousDepth < depth) {
            depthIncrease++
        }

        previousDepth = depth
    }

    return depthIncrease;
}

fun test_day01_step01() {
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

    val actual = day01_step01(testInput)

    println("Test result: ${actual}")
}

fun run_day01_step01() {
    val input = File("input.txt").readText()
    val actual = day01_step01(input)

    println("Result: ${actual}")
}

test_day01_step01()
run_day01_step01()
