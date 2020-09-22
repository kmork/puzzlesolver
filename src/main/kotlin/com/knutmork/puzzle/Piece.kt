package com.knutmork.puzzle

class Piece (private val pos: List<Pair<Int, Int>>, private val id: String){

    fun getAllPositions(): List<Pair<Int, Int>> {
        return pos
    }

    fun getId(): String {
        return id
    }

    fun rotate(): Piece {
        // Rotating 90 degrees clockwise
        val rotatedMap = pos.map { (x, y) -> Pair(y, -x) }
        var minY = rotatedMap.minOf { (x, y) -> y }
        if (minY > 0) {
            minY = 0
        }
        var minX = rotatedMap.minOf { (x, y) -> x }
        if (minX > 0) {
            minX = 0
        }
        return Piece(rotatedMap.map{(x, y) -> Pair(x-minX, y-minY)}, id)
    }

    class Builder() {
        fun simple(shape : String, id : String): Piece {
            val pos = mutableListOf<Pair<Int, Int>>()

            // Transform from simple string format input to a real matrix
            val rows = shape.split("\\s".toRegex())
            val numRows = rows.size
            val numCols = rows[0].length

            // todo: verify that first row has at least one positive cell

            for (x in 0 until numRows) {
                for (y in 0 until numCols) {
                    if ('0' != rows[x][y]) {
                        pos.add(Pair(x, y))
                    }
                }
            }
            return Piece(pos, id)
        }
    }
}
