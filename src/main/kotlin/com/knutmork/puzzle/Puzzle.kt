package com.knutmork.puzzle

fun main(args: Array<String>) {
    Puzzle().start()
}

class Puzzle () {
    val board = Board(6, 13)

    val pieces = initPieces().toMutableList()
    val putStack = ArrayDeque<Piece>()

    fun start() {
        var i = 0
        while (pieces.isNotEmpty()) {
            i += 1
            val piece = pieces.removeLast() // Corresponds to add(last)

            if (board.putPiece(piece)) {
                putStack.addFirst(piece)
            } else {
                // Impossible to add piece to existing board, lets backtrack one step
                backtrack(piece)
            }
            println(board)
            //println(pieces.size)
            if (board.isComplete()) {
                println("COMPLETE !!!")
                println(board)
            }
            //if (i == 100) { break }
        }
        println("ALL PIECES USED")
        println(board)
    }

    private fun backtrack(piece: Piece) {
        pieces.add(piece)
        val lastPiece = putStack.removeFirst()
        if (board.putPieceFromLastPlace(lastPiece)) {
            putStack.addFirst(lastPiece)
        } else {
            backtrack(lastPiece)
        }
    }

    private fun initPieces(): List<Piece> {
        val pieces = mutableListOf<Piece>()

        pieces.add(Piece.Builder().simple("1111 0010", "1"))
        pieces.add(Piece.Builder().simple("11", "2"))
        pieces.add(Piece.Builder().simple("11111", "3"))
        pieces.add(Piece.Builder().simple("1", "4"))
        pieces.add(Piece.Builder().simple("11 11", "5"))
        pieces.add(Piece.Builder().simple("11 10", "6"))
        pieces.add(Piece.Builder().simple("110 011 001", "7"))
        pieces.add(Piece.Builder().simple("101 111", "8"))
        pieces.add(Piece.Builder().simple("11 11", "9"))
        pieces.add(Piece.Builder().simple("110 010 011", "A"))
        pieces.add(Piece.Builder().simple("11", "B"))
        pieces.add(Piece.Builder().simple("1", "C"))
        pieces.add(Piece.Builder().simple("100 111 001", "D"))
        pieces.add(Piece.Builder().simple("1", "E"))
        pieces.add(Piece.Builder().simple("110 011 001", "F"))
        pieces.add(Piece.Builder().simple("010 111 010", "G"))
        pieces.add(Piece.Builder().simple("001 111 001", "H"))
        pieces.add(Piece.Builder().simple("111 010 010", "J"))
        pieces.add(Piece.Builder().simple("10 11 10 10", "K"))
        pieces.add(Piece.Builder().simple("0010 1111", "L"))


//        pieces.add(Piece.Builder().simple("11111", "3"))
//        pieces.add(Piece.Builder().simple("101 111", "8"))
//        pieces.add(Piece.Builder().simple("110 010 011", "A"))
//        pieces.add(Piece.Builder().simple("010 111 010", "G"))
//        pieces.add(Piece.Builder().simple("110 011 001", "F"))
//        pieces.add(Piece.Builder().simple("11 10", "6"))
//        pieces.add(Piece.Builder().simple("100 111 001", "D"))
//        pieces.add(Piece.Builder().simple("0010 1111", "L"))
//        pieces.add(Piece.Builder().simple("10 11 10 10", "K"))
//        pieces.add(Piece.Builder().simple("110 011 001", "7"))
//        pieces.add(Piece.Builder().simple("001 111 001", "H"))
//        pieces.add(Piece.Builder().simple("11", "B"))
//        pieces.add(Piece.Builder().simple("111 010 010", "J"))
//        pieces.add(Piece.Builder().simple("1", "E"))
//        pieces.add(Piece.Builder().simple("1111 0010", "1"))
//        pieces.add(Piece.Builder().simple("11", "2"))
//        pieces.add(Piece.Builder().simple("1", "C"))
//        pieces.add(Piece.Builder().simple("1", "4"))
//        pieces.add(Piece.Builder().simple("11 11", "9"))
//        pieces.add(Piece.Builder().simple("11 11", "5"))

        return pieces
    }
}
