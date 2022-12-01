package griffith.taehyung.assign2_arimaa

import android.graphics.Rect
import griffith.taehyung.assign2_arimaa.Piece.PieceColor
import griffith.taehyung.assign2_arimaa.Piece.PieceType


class GameBoard(tileSize: Int) {
    // declare squares matrix. And with this, declare rectangular
    // matrix according to squares
    var squares = Array(8) { arrayOfNulls<Square>(8) }
    var rects = Array(8) { arrayOfNulls<Rect>(8)}

    // set initial position of pieces
    init {
        removeAllPieces()
        reset(tileSize)
    }

    fun reset(tileSize: Int) {
        for(i in 0..7)
            for(k in 0..7) {
                squares[i][k] = Square()
                rects[i][k] = squares[i][k]!!.rectFromSquare(tileSize, i, k)
            }

        // For Silver
        squares[0][0]!!.havePiece(Piece(PieceColor.SILVER, PieceType.RABBIT))
        squares[0][1]!!.havePiece(Piece(PieceColor.SILVER, PieceType.RABBIT))
        squares[0][2]!!.havePiece(Piece(PieceColor.SILVER, PieceType.RABBIT))
        squares[0][3]!!.havePiece(Piece(PieceColor.SILVER, PieceType.RABBIT))
        squares[0][4]!!.havePiece(Piece(PieceColor.SILVER, PieceType.RABBIT))
        squares[0][5]!!.havePiece(Piece(PieceColor.SILVER, PieceType.RABBIT))
        squares[0][6]!!.havePiece(Piece(PieceColor.SILVER, PieceType.RABBIT))
        squares[0][7]!!.havePiece(Piece(PieceColor.SILVER, PieceType.RABBIT))
        squares[1][0]!!.havePiece(Piece(PieceColor.SILVER, PieceType.CAT))
        squares[1][1]!!.havePiece(Piece(PieceColor.SILVER, PieceType.DOG))
        squares[1][2]!!.havePiece(Piece(PieceColor.SILVER, PieceType.HORSE))
        squares[1][3]!!.havePiece(Piece(PieceColor.SILVER, PieceType.CAMEL))
        squares[1][4]!!.havePiece(Piece(PieceColor.SILVER, PieceType.ELEPHANT))
        squares[1][5]!!.havePiece(Piece(PieceColor.SILVER, PieceType.HORSE))
        squares[1][6]!!.havePiece(Piece(PieceColor.SILVER, PieceType.DOG))
        squares[1][7]!!.havePiece(Piece(PieceColor.SILVER, PieceType.CAT))

        // For Gold
        squares[7][0]!!.havePiece(Piece(PieceColor.GOLD, PieceType.RABBIT))
        squares[7][1]!!.havePiece(Piece(PieceColor.GOLD, PieceType.RABBIT))
        squares[7][2]!!.havePiece(Piece(PieceColor.GOLD, PieceType.RABBIT))
        squares[7][3]!!.havePiece(Piece(PieceColor.GOLD, PieceType.RABBIT))
        squares[7][4]!!.havePiece(Piece(PieceColor.GOLD, PieceType.RABBIT))
        squares[7][5]!!.havePiece(Piece(PieceColor.GOLD, PieceType.RABBIT))
        squares[7][6]!!.havePiece(Piece(PieceColor.GOLD, PieceType.RABBIT))
        squares[7][7]!!.havePiece(Piece(PieceColor.GOLD, PieceType.RABBIT))
        squares[6][0]!!.havePiece(Piece(PieceColor.GOLD, PieceType.CAT))
        squares[6][1]!!.havePiece(Piece(PieceColor.GOLD, PieceType.DOG))
        squares[6][2]!!.havePiece(Piece(PieceColor.GOLD, PieceType.HORSE))
        squares[6][3]!!.havePiece(Piece(PieceColor.GOLD, PieceType.CAMEL))
        squares[6][4]!!.havePiece(Piece(PieceColor.GOLD, PieceType.ELEPHANT))
        squares[6][5]!!.havePiece(Piece(PieceColor.GOLD, PieceType.HORSE))
        squares[6][6]!!.havePiece(Piece(PieceColor.GOLD, PieceType.DOG))
        squares[6][7]!!.havePiece(Piece(PieceColor.GOLD, PieceType.CAT))
    }

    fun removeAllPieces() {
        for(i in 0..7)
            for(k in 0..7)
                squares[i][k]?.dropPiece()
    }
}