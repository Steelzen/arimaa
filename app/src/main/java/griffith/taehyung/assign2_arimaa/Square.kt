package griffith.taehyung.assign2_arimaa

import android.graphics.Rect

class Square {
    // design trap tile (boolean)
    var piece: Piece? = null
    var isEmpty = true

    // data to identify the type of pieces
    var level: Int? = piece?.level
    val color: Piece.PieceColor? = piece?.color

    fun readSquare(): Char {
        return if(isEmpty) {
            ' '
        } else piece!!.letter
    }

    // have new piece on square
    fun havePiece(piece: Piece?) {
        this.piece = piece
        isEmpty = false
    }

    // remove piece on square
    fun dropPiece(): Piece? {
        val droppedPiece: Piece? = piece
        piece = null
        isEmpty = true

        return droppedPiece
    }

    // obtain rectangular by tilesize and squares matrix
    fun rectFromSquare(tileSize: Int, rowInArray: Int, columnInArray: Int): Rect {
        return Rect(columnInArray * tileSize,
            rowInArray * tileSize,
            (columnInArray + 1) * tileSize,
            (rowInArray + 1) * tileSize )
    }
}