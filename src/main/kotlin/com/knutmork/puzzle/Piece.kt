package com.knutmork.puzzle

class Piece (private val shape : String){
    private val pos = mutableListOf<Pair<Int, Int>>()

    init {
        // Transform from simple string format input to a real matrix
        // todo: refactor into builder pattern
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
    }

    fun getAllPositions(): MutableList<Pair<Int, Int>> {
        return pos.toMutableList()
    }

    fun rotate() {

    }
}
