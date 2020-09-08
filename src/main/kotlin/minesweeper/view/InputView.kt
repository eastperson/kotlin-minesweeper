package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Coordinate

object InputView {
    fun inputLength(): Int {
        println("높이를 입력하세요.")
        return changeInt(readLine()) ?: inputLength()
    }

    fun inputWidth(): Int {
        println("너비를 입력하세요.")
        return changeInt(readLine()) ?: inputWidth()
    }

    fun inputMines(): Int {
        println("지뢰는 몇 개인가요?")
        return changeInt(readLine()) ?: inputMines()
    }

    fun inputCoordinate(board: Board): Coordinate {
        print("open:")
        val inputValue = readLine() ?: return inputCoordinate(board)
        val list = inputValue.split(",").map { changeInt(it.trim()) ?: return inputCoordinate(board) }
        checkListSize(list) ?: return inputCoordinate(board)
        board.findPoint(list[0], list[1]) ?: return inputCoordinate(board)
        return Coordinate(list[0], list[1])
    }

    private fun changeInt(inputValue: String?): Int? {
        if (inputValue.isNullOrBlank()) {
            return null
        }
        return try {
            inputValue.toInt()
        } catch (e: Exception) {
            null
        }
    }

    private fun checkListSize(list: List<Int>): Boolean? {
        if (list.size != COORDINATE_SIZE) {
            return null
        }
        return true
    }

    private const val COORDINATE_SIZE = 2
}
