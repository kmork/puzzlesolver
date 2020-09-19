package com.knutmork.puzzle

import org.junit.Test
import kotlin.test.assertEquals

class PieceBuilderTest {

    @Test
    fun testBuilder() {
        val piece = Piece("110 010 011")
        assertEquals(5, piece.getAllPositions().size)
    }
}