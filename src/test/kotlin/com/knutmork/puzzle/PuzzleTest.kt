package com.knutmork.puzzle

import org.junit.Test

class PuzzleTest {

    @Test
    fun simplePuzzle() {
        val board = Board(6, 13)
        board.putPiece(Piece("110 010 011"))
        board.putPiece(Piece("110 010 011"))
        board.putPiece(Piece("111111"))

        println(board)
    }
}