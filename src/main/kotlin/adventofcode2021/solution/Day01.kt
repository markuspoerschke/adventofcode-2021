package adventofcode2021.solution

import adventofcode2021.PuzzleInput
import adventofcode2021.Solution

class Day01(puzzleInput: PuzzleInput) : Solution<Int>(puzzleInput) {
    private val depths = puzzleInput.linesAsInts()

    override fun part1(): Int = depths.countDecreases()

    override fun part2(): Int = depths
        .windowed(3)
        .map { it.sum() }
        .countDecreases()
}

private fun List<Int>.countDecreases(): Int = zipWithNext().count { (a, b) -> b > a }
