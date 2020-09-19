package com.knutmork.puzzle

import org.junit.Test

class PuzzleTest {

    @Test
    fun simplePuzzle() {
        val piece1 = Piece("110 010 011")
        val board = Board(6, 13)
        board.putPiece(piece1)

        println(board)
    }
}