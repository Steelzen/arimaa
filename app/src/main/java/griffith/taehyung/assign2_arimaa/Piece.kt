package griffith.taehyung.assign2_arimaa

class Piece {
    enum class PieceColor {
        GOLD, SILVER
    }

    enum class PieceType {
        RABBIT, CAT, DOG, HORSE, CAMEL, ELEPHANT
    }

    constructor(color: PieceColor, type: PieceType) {
        this.color = color
        this.type = type
    }

    var color: PieceColor
        set
        get() = color

    var type: PieceType
        set
        get() = type

    // representative letter to identify type and color of piece
    val letter: Char
        get() = when(type) {
            PieceType.RABBIT -> if(color == PieceColor.GOLD) 'R' else 'r'
            PieceType.CAT -> if(color == PieceColor.GOLD) 'C' else 'c'
            PieceType.DOG -> if(color == PieceColor.GOLD) 'D' else 'd'
            PieceType.HORSE -> if(color == PieceColor.GOLD) 'H' else 'h'
            PieceType.CAMEL -> if(color == PieceColor.GOLD) 'M' else 'm'
            PieceType.ELEPHANT -> if(color == PieceColor.GOLD) 'E' else 'e'
        }

    // level of pieces from weak to strong
    val level: Int
        get() = when(type) {
            PieceType.RABBIT -> 1
            PieceType.CAT -> 2
            PieceType.DOG -> 3
            PieceType.HORSE -> 4
            PieceType.CAMEL -> 5
            PieceType.ELEPHANT -> 6
        }

    fun isLarger(piece: Piece): Boolean {
        return level > piece.level
    }
}