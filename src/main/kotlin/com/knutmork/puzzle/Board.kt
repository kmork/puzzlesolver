package com.knutmork.puzzle

import java.lang.StringBuilder

class Board (private val numCols : Int, private val numRows : Int){

    private var matrix: Array<Array<Cell>>

    init {
        matrix = Array(numRows) {Array(numCols) { Cell() }}
    }

    fun putPiece(piece : Piece) {
        var piecePut = false

        var (x, y) = findFirstEmptyPos(0)
        piecePut = putPieceIfAllEmptyCellsRotateIfNeeded(piece, x, y)

        while (!piecePut) {

            var (x1, y1) = findFirstEmptyPos(convertPosToSequence(x, y))
            x = x1
            y = y1
            piecePut = putPieceIfAllEmptyCellsRotateIfNeeded(piece, x, y)
        }
    }

    private fun putPieceIfAllEmptyCellsRotateIfNeeded(piece: Piece, x: Int, y: Int): Boolean {
        var pieceWorking = piece // create a working copy that can be manipulated

        if (!putPieceIfAllEmptyCells(pieceWorking, x, y, findRelativePositions(pieceWorking, x, y))) {
            pieceWorking = pieceWorking.rotate()
            if (!putPieceIfAllEmptyCells(pieceWorking, x, y, findRelativePositions(pieceWorking, x, y))) {
                pieceWorking = pieceWorking.rotate()
                if (!putPieceIfAllEmptyCells(pieceWorking, x, y, findRelativePositions(pieceWorking, x, y))) {
                    pieceWorking = pieceWorking.rotate()
                    return putPieceIfAllEmptyCells(pieceWorking, x, y, findRelativePositions(pieceWorking, x, y))
                }
            }
        }
        return true
    }

    private fun findRelativePositions(piece: Piece, x: Int, y: Int): List<Pair<Int, Int>> {
        return piece.getAllPositions().map { (x1, y1) -> Pair(x1 + x, y1 + y) }
    }

    private fun putPieceIfAllEmptyCells(piece: Piece, x: Int, y: Int, relPositions: List<Pair<Int, Int>>): Boolean {
        val allEmpty = allEmpty(relPositions)
        if (allEmpty) {
            cellAtPos(x, y).setPiece(piece)
            for ((x1, y1) in relPositions.iterator()) {
                cellAtPos(x1, y1).setPiece(piece)
            }
        }
        return allEmpty
    }

    private fun allEmpty(boardPositions: List<Pair<Int, Int>>): Boolean {
        for ((x, y) in boardPositions.iterator()) {
            if (!cellAtPos(x, y).isEmpty()) {
                return false
            }
        }
        return true
    }

    private fun findFirstEmptyPos(seq: Int) : Pair<Int, Int> {
        for (x in 0 until numRows) {
            for (y in 0 until numCols) {
                if (convertPosToSequence(x, y) > seq) {
                    if (cellAtPos(x, y).isEmpty()) {
                        return Pair(x, y)
                    }
                }
            }
        }
        return Pair(-1, -1)
    }

    private fun convertPosToSequence(x: Int, y: Int): Int {
        return y + 1 + (x * numRows)
    }

    private fun cellAtPos(x : Int, y : Int) : Cell {
        if (x < 0 || x > numRows -1 || y < 0 || y > numCols - 1) {
            return Cell().setPiece(Piece.Builder().simple("", "")) // To prevent out-of-border, perhaps too hacky?
        } else {
            return matrix[x][y]
        }
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

        fun setPiece(piece : Piece): Cell{
            pieceRef = piece
            return this
        }

        override fun toString () : String {
            return if (isEmpty()) "  " else pieceRef?.getId() + " "
        }
    }
}