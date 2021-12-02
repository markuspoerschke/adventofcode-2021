package adventofcode2021.solution

import adventofcode2021.PuzzleInput
import adventofcode2021.Solution

class Day02(puzzleInput: PuzzleInput) : Solution<Int>(puzzleInput) {
    private val instructions : List<Instruction> = puzzleInput.linesAsStrings().map {
        val (directionAsString, lengthAsString) = it.split(" ")

        Instruction(Direction.valueOf(directionAsString.uppercase()), lengthAsString.toInt())
    }

    override fun part1(): Int {
        var horizontalPosition = 0
        var depth = 0

        instructions.forEach {
            when (it.direction) {
                Direction.FORWARD -> horizontalPosition += it.amount
                Direction.UP -> depth -= it.amount
                Direction.DOWN -> depth += it.amount
            }
        }

        return horizontalPosition * depth
    }

    override fun part2(): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0

        instructions.forEach {
            when (it.direction) {
                Direction.UP -> aim += it.amount
                Direction.DOWN -> aim -= it.amount
                Direction.FORWARD -> {
                    horizontalPosition += it.amount
                    // since we're on a submarine,
                    // down and up do the opposite of what we might expect:
                    // "down" means aiming in the positive direction.
                    depth -= aim * it.amount
                }
            }
        }

        return horizontalPosition * depth
    }

    enum class Direction {
        FORWARD,
        DOWN,
        UP,
    }

    data class Instruction(val direction: Direction, val amount: Int)
}
