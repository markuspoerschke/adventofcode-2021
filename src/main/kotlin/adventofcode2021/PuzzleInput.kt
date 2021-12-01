package adventofcode2021

import java.io.File

class PuzzleInput(private val fileName: String) {
    val text by lazy { ClassLoader.getSystemResource(fileName)?.readText().orEmpty() }
    fun linesAsStrings(): List<String> = text.split("\n")
    fun linesAsInts(): List<Int> = linesAsStrings().map { it.toInt() }
}
