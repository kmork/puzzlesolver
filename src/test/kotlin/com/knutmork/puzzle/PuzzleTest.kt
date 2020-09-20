package com.knutmork.puzzle

import org.junit.Test

class PuzzleTest {

    @Test
    fun simplePuzzle() {
        val board = Board(6, 12)
        board.putPiece(Piece("110 010 011"))
        board.putPiece(Piece("110 010 011"))
        board.putPiece(Piece("111111"))

        println(board)
    }

    @Test
    fun testNextRow() {
        val board = Board(6, 12)
        board.putPiece(Piece("111111"))
        board.putPiece(Piece("111 111"))

        println(board)
    }
}