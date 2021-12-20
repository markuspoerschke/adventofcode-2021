package adventofcode2021.solution

import adventofcode2021.PuzzleInput
import adventofcode2021.Solution

class Day04(puzzleInput: PuzzleInput) : Solution<Int>(puzzleInput) {
    override fun part1(): Int {
        val bingoInput = puzzleInput.bingoInput()
        val boards = puzzleInput.bingoBoards()

        for (drawnNumber in bingoInput) {
            for (board in boards) {
                board.addDrawnNumber(drawnNumber)
                if (board.isBingo()) {
                    return board.sumUnmarkedNumbers() * drawnNumber
                }
            }
        }

        return 0
    }

    override fun part2(): Int {
        val bingoInput = puzzleInput.bingoInput()
        val boards = puzzleInput.bingoBoards()
        val winners = mutableListOf<BingoBoard>()

        for (drawnNumber in bingoInput) {
            for (board in boards.filter { !winners.contains(it) }) {
                board.addDrawnNumber(drawnNumber)
                if (board.isBingo()) {
                    winners.add(board)
                }

                if (winners.size == boards.size) {
                    return winners.last().sumUnmarkedNumbers() * drawnNumber
                }
            }
        }

        return 0
    }
}

private fun PuzzleInput.bingoInput(): List<Int> = linesAsStrings().first().split(",").map { it.toInt() }

private fun PuzzleInput.bingoBoards(): List<BingoBoard> {
    return linesAsStrings()
        .drop(1)
        .filter { it.isNotBlank() }
        .chunked(5)
        .map { rows ->
            BingoBoard(
                rows.map { row ->
                    row
                        .chunked(3)
                        .map { it.trim() }
                        .map { number -> number.toInt() }
                }
            )
        }
}

class BingoBoard(private val rows: List<List<Int>>) {
    private val drawnNumbers = mutableListOf<Int>()

    fun addDrawnNumber(number: Int) {
        drawnNumbers.add(number)
    }

    fun sumUnmarkedNumbers(): Int {
        return rows
            .flatten()
            .filter { number -> !drawnNumbers.contains(number) }
            .sum()
    }

    fun isBingo(): Boolean {
        if (rows.any { number -> number.all { drawnNumbers.contains(it) } }) {
            return true
        }

        return rows.indices
            .map { rows.map { row -> row[it] } }
            .any { column ->
                column.all { number -> drawnNumbers.contains(number) }
            }
    }
}