package griffith.taehyung.assign2_arimaa

import android.graphics.Rect

class Square {
    var piece: Piece? = null
    var isEmpty = true

    // obtain rectangular by tilesize and squares matrix
    fun rectFromSquare(tileSize: Int, rowInArray: Int, columnInArray: Int): Rect {
        return Rect(columnInArray * tileSize,
            rowInArray * tileSize,
            (columnInArray + 1) * tileSize,
            (rowInArray + 1) * tileSize )
    }

    fun readSquare(): Char {
        return if(isEmpty) {
            '.'
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
}