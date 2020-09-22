package com.knutmork.puzzle

import org.junit.Test
import kotlin.test.assertEquals

class PieceTest {

    @Test
    fun testBuilder() {
        val piece = Piece.Builder().simple("110 010 011", "A")
        assertEquals(5, piece.getAllPositions().size)
    }

    @Test
    fun testBuilderLongSingleRow() {
        val piece = Piece.Builder().simple("111111", "B")
        assertEquals(6, piece.getAllPositions().size)
    }

    @Test
    fun testRotate180() {
        val piece = Piece.Builder().simple("1111", "")
        //val piece = Piece.Builder().simple("0101 1111", "")
        val pieceRotated90 = piece.rotate()
        val pieceRotated180 = piece.rotate().rotate()
        val pieceRotated270 = piece.rotate().rotate().rotate()
        val pieceRotated360 = piece.rotate().rotate().rotate().rotate()
        assertEquals(1, 1)
    }
}