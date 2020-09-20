package com.knutmork.puzzle

import org.junit.Test
import kotlin.test.assertEquals

class PieceBuilderTest {

    @Test
    fun testBuilder() {
        val piece = Piece("110 010 011")
        assertEquals(5, piece.getAllPositions().size)
    }

    @Test
    fun testLongSingleRow() {
        val piece = Piece("111111")
        assertEquals(6, piece.getAllPositions().size)
    }
}