package com.knutmork.puzzle

import java.lang.StringBuilder
import kotlin.system.exitProcess

class Board (private val numCols : Int, private val numRows : Int){

    private var matrix: Array<Array<Cell>>
    private val log = mutableMapOf<String, PiecePosition>()

    init {
        matrix = Array(numRows) {Array(numCols) { Cell() }}
    }

    fun putPiece(piece : Piece): Boolean {
        return putPieceFromPosition(piece, Pos(0, -1)) // Start position
    }

    fun putPieceFromPosition(piece : Piece, pos: Pos): Boolean {
        var p = pos.copy()
        var piecePut = false
        while (!piecePut) {

            p = findFirstEmptyPos(convertPosToSequence(p))
            if (p.x == -1 && p.y == -1) { // No empty pos found
                return false
            }
            piecePut = putPieceIfAllEmptyCellsRotateIfNeeded(piece, p)
        }
        return piecePut
    }

    fun putPieceFromLastPlace(piece: Piece): Boolean {
        val position = log[piece.getId()];
        position?.let {
            removePiece(piece)
            val piecePut = putPieceFromPosition(piece, posMinus1(it.pos))
            if (!piecePut) {
                removeLogPiece(piece) // Remove the old log entry
            }
            return piecePut
        }
        if (position == null) { // No need for this really
            println("ERROR: NO POSITION FOUND")
            exitProcess(1)
        }
        return false
    }

    fun isComplete(): Boolean {
        for (rows in matrix.iterator()) {
            for (cells in rows.iterator()) {
                if (cells.isEmpty()) {
                    return false
                }
            }
        }
        return true
    }

    private fun posMinus1(pos: Pos): Pos {
        if (pos.y > 0) {
            return Pos(pos.x, pos.y - 1)
        } else {
            return Pos(pos.x - 1, numCols - 1)
        }
    }

    private fun removePiece(piece: Piece) {
        for (row in matrix.iterator()) {
            for (cell in row.iterator()) {
                if (cell.hasPiece(piece)) {
                    cell.removePiece()
                }
            }
        }
    }

    private fun putPieceIfAllEmptyCellsRotateIfNeeded(piece: Piece, pos: Pos): Boolean {
        var pieceWorking = piece // create a working copy for manipulation
        var piecePut = false

        log[piece.getId()]?.let {
            repeat(it.rotation) {
                pieceWorking = pieceWorking.rotate()
            }
        }

        while (!piecePut && pieceWorking.getRotation() < 4) {
            pieceWorking = pieceWorking.rotate()
            piecePut = putPieceIfAllEmptyCells(pieceWorking, pos, findRelativePositions(pieceWorking, pos))
        }

        if (piecePut) {
            logPiece(pieceWorking, pos)
        }
        return piecePut
    }

    private fun logPiece(piece: Piece, pos: Pos) {
        log[piece.getId()] = PiecePosition(pos, piece.getFlip(), piece.getRotation())
    }

    private fun removeLogPiece(piece: Piece) {
        log.remove(piece.getId())
    }

    private fun findRelativePositions(piece: Piece, pos: Pos): List<Pos> {
        return piece.getAllPositions().map { (x1, y1) -> Pos(x1 + pos.x, y1 + pos.y) }
    }

    private fun putPieceIfAllEmptyCells(piece: Piece, pos: Pos, relPositions: List<Pos>): Boolean {
        val allEmpty = allEmpty(relPositions)
        if (allEmpty) {
            cellAtPos(pos).setPiece(piece)
            for (p in relPositions.iterator()) {
                cellAtPos(p).setPiece(piece)
            }
        }
        return allEmpty
    }

    private fun allEmpty(boardPositions: List<Pos>): Boolean {
        for ((x, y) in boardPositions.iterator()) {
            if (!cellAtPos(Pos(x, y)).isEmpty()) {
                return false
            }
        }
        return true
    }

    private fun findFirstEmptyPos(seq: Int) : Pos {
        for (x in 0 until numRows) {
            for (y in 0 until numCols) {
                if (convertPosToSequence(Pos(x, y)) > seq) {
                    if (cellAtPos(Pos(x, y)).isEmpty()) {
                        return Pos(x, y)
                    }
                }
            }
        }
        return Pos(-1, -1)
    }

    private fun convertPosToSequence(p: Pos): Int {
        return p.y + 1 + (p.x * numRows)
    }

    private fun cellAtPos(pos: Pos) : Cell {
        if (pos.x < 0 || pos.x > numRows -1 || pos.y < 0 || pos.y > numCols - 1) {
            return Cell().setPiece(Piece.Builder().simple("", "")) // To prevent out-of-border, perhaps too hacky?
        } else {
            return matrix[pos.x][pos.y]
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

        fun hasPiece(piece: Piece): Boolean {
            return (piece.getId() == pieceRef?.getId())
        }

        fun removePiece() {
            pieceRef = null
        }

        override fun toString () : String {
            return if (isEmpty()) "  " else pieceRef?.getId() + " "
        }
    }

    data class PiecePosition(val pos: Pos, val flip: Int, val rotation: Int)
}