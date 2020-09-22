package com.knutmork.puzzle

import org.junit.Test

class PuzzleTest {

    @Test
    fun simplePuzzle() {
        val board = Board(6, 12)
        board.putPiece(Piece.Builder().simple("110 010 011", "1"))
        board.putPiece(Piece.Builder().simple("110 010 011", "2"))
        board.putPiece(Piece.Builder().simple("111111", "3"))
        board.putPiece(Piece.Builder().simple("11", "4"))
        board.putPiece(Piece.Builder().simple("0101 1111", "5"))

        println(board)
    }

    @Test
    fun testNextRow() {
        val board = Board(6, 12)
        board.putPiece(Piece.Builder().simple("111111", "1"))
        board.putPiece(Piece.Builder().simple("111 111", "2"))

        println(board)
    }
}