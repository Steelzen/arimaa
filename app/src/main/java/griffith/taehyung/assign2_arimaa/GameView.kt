package griffith.taehyung.assign2_arimaa

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.Stack

class GameView(context: Context?, attribs: AttributeSet?): View(context, attribs){
    private var _context: Context? = context
    private var _attribs: AttributeSet? = null

    enum class GameStatus {
        GOLDTURN, SILVERTURN, GOLDWIN, SILVERWIN
    }

    var gameStatus = GameStatus.GOLDTURN

    // total numbers of moves: 4
    var moves: Int = 4

    private var width: Int? = 0
    private var height: Int?  = 0

    private var _pointers: Int = 0

    var moveable: Bitmap? = null
    var held: Bitmap? = null

    // Point Origin and destination
    var src: Point = Point()
    var dst: Point = Point()

    // history stack to keep a gameboard state
    var boardHistoryStack: Stack<String>? = Stack()
    var tilesize = 0
    var isUpdated: Boolean = false

    lateinit var gameBoard: GameBoard

    // set of pieces images
    val imgResourceIDs = setOf(
        R.drawable.camel_gold,
        R.drawable.camel_silver,
        R.drawable.cat_gold,
        R.drawable.cat_silver,
        R.drawable.dog_gold,
        R.drawable.dog_silver,
        R.drawable.horse_gold,
        R.drawable.horse_silver,
        R.drawable.rabbit_gold,
        R.drawable.rabbit_silver,
        R.drawable.elephant_gold,
        R.drawable.elephant_silver
    )

    val bitmaps = mutableMapOf<Int, Bitmap>()

    init {
        loadBitmaps()
    }

    // initialize drawable for drawing pieces
    val paint: Paint = Paint()

    val goldElephant = bitmaps[R.drawable.elephant_gold]!!
    val goldCamel = bitmaps[R.drawable.camel_gold]!!
    val goldCat = bitmaps[R.drawable.cat_gold]!!
    val goldDog = bitmaps[R.drawable.dog_gold]!!
    val goldHorse = bitmaps[R.drawable.horse_gold]!!
    val goldRabbit = bitmaps[R.drawable.rabbit_gold]!!

    val silverElephant = bitmaps[R.drawable.elephant_silver]!!
    val silverCamel = bitmaps[R.drawable.camel_silver]!!
    val silverCat = bitmaps[R.drawable.cat_silver]!!
    val silverDog = bitmaps[R.drawable.dog_silver]!!
    val silverHorse = bitmaps[R.drawable.horse_silver]!!
    val silverRabbit = bitmaps[R.drawable.rabbit_silver]!!


    fun setTileSize(width: Int) {
        tilesize = width / TILES
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
    }

    // set to square view
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (widthMeasureSpec < heightMeasureSpec)
            super.onMeasure(
            widthMeasureSpec,
            widthMeasureSpec
        ) else super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        if(!isUpdated)
            gameBoard = GameBoard(tilesize)

        drawBoard(canvas)
        drawPieces(canvas)

        // put initial board state to board history stack
        boardHistoryStack?.push(gameBoard.boardState)
        println("Game state: " + boardHistoryStack?.peek())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event ?: return false

        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                src = Point(event.y.toInt() / tilesize, event.x.toInt() / tilesize)
                println( "source: (${src.x}, ${src.y})")
                if(gameBoard.squares[src.x][src.y]?.isEmpty == true)
                    return false
            }
            MotionEvent.ACTION_MOVE -> {

            }
            MotionEvent.ACTION_UP -> {
                dst = Point(event.y.toInt() / tilesize, event.x.toInt() / tilesize)
                if(dst.x > 7 || dst.x < 0 || dst.y > 7 || dst.y < 0){
                    println("Invalid Move")
                    return false
                }
                if(gameBoard.squares[dst.x][dst.y]?.isEmpty == false) {
                    println("Cannot move to this")
                    return false
                }
                movePiece(src,dst)
                boardHistoryStack?.push(gameBoard.boardState)
                println("from (${src.x}, ${src.y}) to (${dst.x}, ${dst.y})")
                println("new state: " + boardHistoryStack?.peek())
            }
        }
        moves--
        isUpdated = true
        invalidate()
        return true
    }

    fun drawBoard(canvas: Canvas?) {
        val fillPaint = Paint()
        val strokePaint = Paint()

        fillPaint.setStyle(Paint.Style.FILL)
        strokePaint.setStyle(Paint.Style.STROKE)
        strokePaint.setColor(Color.BLACK)

        for (i in 0 until TILES) {
            for (k in 0 until TILES) {
                if(i == 2 && k == 2 || i == 5 && k ==2 || i == 2 && k == 5 || i == 5 && k == 5) {
                    fillPaint.setColor(Color.DKGRAY)
                } else
                    fillPaint.setColor(Color.LTGRAY)

                canvas?.drawRect(
                    0f+tilesize * i,
                    0f+tilesize * k,
                    0f+tilesize+tilesize*i,
                    0f+tilesize+tilesize*k,
                    fillPaint)
                canvas?.drawRect(
                    0f+tilesize * i,
                    0f+tilesize * k,
                    0f+tilesize+tilesize*i,
                    0f+tilesize+tilesize*k,
                    strokePaint)
            }
        }
    }

    fun drawPieces(canvas: Canvas?) {
        canvas?.save()
        // dynamically draw pieces on board depending on board state
        for (i in 0..7)
            for(k in 0 .. 7) {
                val letter = gameBoard.squares[i][k]?.readSquare() // letter of pieces

                when(letter) {
                    'R' -> canvas?.drawBitmap(goldRabbit, null, gameBoard.rects[i][k]!!, paint)
                    'r' -> canvas?.drawBitmap(silverRabbit, null, gameBoard.rects[i][k]!!, paint)
                    'C' -> canvas?.drawBitmap(goldCat, null, gameBoard.rects[i][k]!!, paint)
                    'c' -> canvas?.drawBitmap(silverCat, null, gameBoard.rects[i][k]!!, paint)
                    'D' -> canvas?.drawBitmap(goldDog, null, gameBoard.rects[i][k]!!, paint)
                    'd' -> canvas?.drawBitmap(silverDog, null, gameBoard.rects[i][k]!!, paint)
                    'H' -> canvas?.drawBitmap(goldHorse, null, gameBoard.rects[i][k]!!, paint)
                    'h' -> canvas?.drawBitmap(silverHorse, null, gameBoard.rects[i][k]!!, paint)
                    'M' -> canvas?.drawBitmap(goldCamel, null, gameBoard.rects[i][k]!!, paint)
                    'm' -> canvas?.drawBitmap(silverCamel, null, gameBoard.rects[i][k]!!, paint)
                    'E' -> canvas?.drawBitmap(goldElephant, null, gameBoard.rects[i][k]!!, paint)
                    'e' -> canvas?.drawBitmap(silverElephant, null, gameBoard.rects[i][k]!!, paint)
                }
            }
        canvas?.restore()
    }

    fun loadBitmaps() {
        imgResourceIDs.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    fun movePiece(p1: Point, p2: Point) {
        gameBoard!!.movePiece(p1, p2)
    }

    fun resetGame() {
        gameBoard!!.reset()
        gameStatus = GameStatus.GOLDTURN
        moves = 4
        invalidate()
    }

    fun updateGameStatus() {

    }

    /** constant values **/
    companion object {
        const val TILES = 8
    }


}



