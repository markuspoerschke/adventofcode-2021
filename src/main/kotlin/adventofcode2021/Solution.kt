package adventofcode2021

abstract class Solution<R>(protected val puzzleInput: PuzzleInput) {
    abstract fun part1(): R?
    abstract fun part2(): R?
}
