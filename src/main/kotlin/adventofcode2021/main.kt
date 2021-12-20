package adventofcode2021

import kotlin.reflect.full.primaryConstructor

fun main(args: Array<String>) {
    // todo extract day from args
    val day = 4

    val solutionClassName = "adventofcode2021.solution.${day.toDayName()}"
    val solution = Class.forName(solutionClassName)
        .kotlin
        // todo run test and final result
        .primaryConstructor?.call(PuzzleInput("Day${day.toString().padStart(2, '0')}.txt"))

    if (solution !is Solution<*>) {
        error("Class $solutionClassName is not a instance of Solution")
    }

    println(day.toDayName())
    println("Part 1: ${solution.part1()}")
    println("Part 2: ${solution.part2()}")
}

private fun Int.toDayName(): String = "Day" + toString().padStart(2, '0')
