package com.knutmork.puzzle

class Piece (private val pos: List<Pos>, private val id: String, private val flip: Int, private val rotation: Int){

    fun getRotation(): Int {
        return rotation
    }

    fun getFlip(): Int {
        return flip
    }

    fun getAllPositions(): List<Pos> {
        return pos
    }

    fun getId(): String {
        return id
    }

    // TODO: Missing flipping piece (and then rotate 3 times)
    fun rotate(): Piece {
        // Rotating 90 degrees clockwise
        val rotatedMap = pos.map { p -> Pos(p.y, -p.x) }
        var minY = rotatedMap.minOf { p -> p.y }
        if (minY > 0) {
            minY = 0
        }
        var minX = rotatedMap.minOf { p -> p.x }
        if (minX > 0) {
            minX = 0
        }
        return Piece(rotatedMap.map{ p -> Pos(p.x-minX, p.y-minY)}, id, flip, rotation+1)
    }

    class Builder() {
        fun simple(shape : String, id : String): Piece {
            val pos = mutableListOf<Pos>()

            // Transform from simple string format input to a real matrix
            val rows = shape.split("\\s".toRegex())
            val numRows = rows.size
            val numCols = rows[0].length

            // todo: verify that first row has at least one positive cell

            for (x in 0 until numRows) {
                for (y in 0 until numCols) {
                    if ('0' != rows[x][y]) {
                        pos.add(Pos(x, y))
                    }
                }
            }
            return Piece(pos, id, 0, 0)
        }
    }
}
