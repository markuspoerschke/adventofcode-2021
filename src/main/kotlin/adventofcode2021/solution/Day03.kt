package adventofcode2021.solution

import adventofcode2021.PuzzleInput
import adventofcode2021.Solution

class Day03(puzzleInput: PuzzleInput) : Solution<Int>(puzzleInput) {
    private val numbers = puzzleInput.linesAsStrings()
    private val numberLength = numbers.first().length

    override fun part1(): Int {
        var gamma = ""
        var epsilon = ""

        for (i in 0 until numberLength) {
            val bits = findMostAndLeastCommonBit(numbers, i)
            gamma += bits.first
            epsilon += bits.second
        }

        return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
    }

    override fun part2(): Int {
        var oxygenGeneratorRatingCandidates = numbers
        var co2ScrubberRatingCandidates = numbers

        for (i in 0 until numberLength) {
            if (oxygenGeneratorRatingCandidates.count() > 1) {
                val mostCommonBit = findMostAndLeastCommonBit(oxygenGeneratorRatingCandidates, i)
                oxygenGeneratorRatingCandidates = oxygenGeneratorRatingCandidates.filter { it[i] == mostCommonBit.first }
            }

            if (co2ScrubberRatingCandidates.count() > 1) {
                val mostCommonBit = findMostAndLeastCommonBit(co2ScrubberRatingCandidates, i)
                co2ScrubberRatingCandidates = co2ScrubberRatingCandidates.filter { it[i] == mostCommonBit.second }
            }
        }

        val oxygenGeneratorRating = oxygenGeneratorRatingCandidates.first()
        val co2ScrubberRating = co2ScrubberRatingCandidates.first()

        return Integer.parseInt(oxygenGeneratorRating, 2) * Integer.parseInt(co2ScrubberRating, 2)
    }

    /**
     * The first value of the pair is the most common bit, and the second value is the least common bit.
     */
    private fun findMostAndLeastCommonBit(numbers: List<String>, position: Int): Pair<Char, Char> {
        val partitions = numbers.partition { it[position] == '1' }

        if (partitions.first.count() >= partitions.second.count()) {
            return Pair('1', '0')
        }

        return Pair('0', '1')
    }
}