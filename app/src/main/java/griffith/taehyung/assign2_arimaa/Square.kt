package griffith.taehyung.assign2_arimaa

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
}