package com.knutmork.puzzle

import org.junit.Test

class PieceBuilderTest {

    @Test
    fun testBuilder() {
        val piece = Piece("110 010 011")
        println(piece.getAllPositions())
    }
}