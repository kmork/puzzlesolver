package com.knutmork.puzzle

import java.lang.StringBuilder

class Board (private val numCols : Int, private val numRows : Int){

    private var matrix: Array<Array<Cell>>

    init {
        matrix = Array(numRows) {Array(numCols) { Cell() }}
    }

    fun putPiece(piece : Piece) {
        val (x, y) = findFirstEmptyPos()
        checkEmptyCellsForPiece(piece, x, y)
        cellAtPos(x, y).setPiece(piece)
        // putRotateIfNecessary()
    }

    private fun checkEmptyCellsForPiece(piece: Piece, x: Int, y: Int) {
        val piecePositions = piece.getAllPositions()

        // Then get relative x.y for rest of cells in piece and map them to empty cells on board
    }

    private fun findFirstEmptyPos() : Pair<Int, Int> {
        return findFirstEmptyPos(0, 0)
    }

    private fun findFirstEmptyPos(startX: Int, startY: Int) : Pair<Int, Int> {
        for (x in startX until numRows) {
            for (y in startY until numCols) {
                if (cellAtPos(x, y).isEmpty()) {
                    return Pair(x, y)
                }
            }
        }
        return Pair(-1, -1)
    }

    private fun cellAtPos(x : Int, y : Int) : Cell {
        return matrix[x][y]
    }

    override fun toString() : String {
        val output = StringBuilder()
        for (rows in matrix.iterator()) {
            for (cells in rows.iterator()) {
                output.append(cells)
            }
            output.append("\n")
        }
        return output.toString()
    }

    class Cell {
        private var pieceRef : Piece? = null

        fun isEmpty () : Boolean {
            return (pieceRef == null)
        }

        fun setPiece(piece : Piece) {
            pieceRef = piece
        }

        override fun toString () : String {
            return if (isEmpty()) "0 " else "1 "
        }
    }
}