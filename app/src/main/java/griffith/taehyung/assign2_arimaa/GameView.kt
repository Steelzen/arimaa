package griffith.taehyung.assign2_arimaa

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.Stack

class GameView(context: Context?): View(context){
    private var _context: Context? = context
    private var _attribs: AttributeSet? = null

    private var width: Int? = 0
    private var height: Int?  = 0

    var pieces: Bitmap? = null
    var moveable: Bitmap? = null
    var held: Bitmap? = null

    // history stack to keep a gameboard state
    var boardHistoryStack: Stack<String>? = Stack()
    var pieceset = 0
    var tilesize = 0

    lateinit var gameBoard: GameBoard

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

    constructor(context: Context, attrs: AttributeSet?) : this(context) {
        _attribs = attrs
    }

    init {
        loadBitmaps()
    }

    fun setTileSize(windowWidth: Int) {
        tilesize = windowWidth / TILES
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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        setTileSize(width!!)
        gameBoard = GameBoard(tilesize)

        drawBoard(canvas)

        canvas?.save()
        drawPiece(canvas)

        // put initial board state to board history stack
        boardHistoryStack?.push(gameBoard.boardState)
        canvas?.restore()

        println("Previous board state: " + boardHistoryStack?.peek())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
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

    fun drawPiece(canvas: Canvas?) {
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

        canvas?.drawBitmap(goldRabbit, null, gameBoard.rects[7][0]!!, paint)
        canvas?.drawBitmap(goldRabbit, null, gameBoard.rects[7][1]!!, paint)
        canvas?.drawBitmap(goldRabbit, null, gameBoard.rects[7][2]!!, paint)
        canvas?.drawBitmap(goldRabbit, null, gameBoard.rects[7][3]!!, paint)
        canvas?.drawBitmap(goldRabbit, null, gameBoard.rects[7][4]!!, paint)
        canvas?.drawBitmap(goldRabbit, null, gameBoard.rects[7][5]!!, paint)
        canvas?.drawBitmap(goldRabbit, null, gameBoard.rects[7][6]!!, paint)
        canvas?.drawBitmap(goldRabbit, null, gameBoard.rects[7][7]!!, paint)
        canvas?.drawBitmap(goldCat, null, gameBoard.rects[6][0]!!, paint)
        canvas?.drawBitmap(goldDog, null, gameBoard.rects[6][1]!!, paint)
        canvas?.drawBitmap(goldHorse, null, gameBoard.rects[6][2]!!, paint)
        canvas?.drawBitmap(goldCamel, null, gameBoard.rects[6][3]!!, paint)
        canvas?.drawBitmap(goldElephant, null, gameBoard.rects[6][4]!!, paint)
        canvas?.drawBitmap(goldHorse, null, gameBoard.rects[6][5]!!, paint)
        canvas?.drawBitmap(goldDog, null, gameBoard.rects[6][6]!!, paint)
        canvas?.drawBitmap(goldCat, null, gameBoard.rects[6][7]!!, paint)

        canvas?.drawBitmap(silverRabbit, null, gameBoard.rects[0][0]!!, paint)
        canvas?.drawBitmap(silverRabbit, null, gameBoard.rects[0][1]!!, paint)
        canvas?.drawBitmap(silverRabbit, null, gameBoard.rects[0][2]!!, paint)
        canvas?.drawBitmap(silverRabbit, null, gameBoard.rects[0][3]!!, paint)
        canvas?.drawBitmap(silverRabbit, null, gameBoard.rects[0][4]!!, paint)
        canvas?.drawBitmap(silverRabbit, null, gameBoard.rects[0][5]!!, paint)
        canvas?.drawBitmap(silverRabbit, null, gameBoard.rects[0][6]!!, paint)
        canvas?.drawBitmap(silverRabbit, null, gameBoard.rects[0][7]!!, paint)
        canvas?.drawBitmap(silverCat, null, gameBoard.rects[1][0]!!, paint)
        canvas?.drawBitmap(silverDog, null, gameBoard.rects[1][1]!!, paint)
        canvas?.drawBitmap(silverHorse, null, gameBoard.rects[1][2]!!, paint)
        canvas?.drawBitmap(silverCamel, null, gameBoard.rects[1][3]!!, paint)
        canvas?.drawBitmap(silverElephant, null, gameBoard.rects[1][4]!!, paint)
        canvas?.drawBitmap(silverHorse, null, gameBoard.rects[1][5]!!, paint)
        canvas?.drawBitmap(silverDog, null, gameBoard.rects[1][6]!!, paint)
        canvas?.drawBitmap(silverCat, null, gameBoard.rects[1][7]!!, paint)
    }

    fun loadBitmaps() {
        imgResourceIDs.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    // function to represent array index from point
    fun gerArrayIndexFromPoint (num: Int): Int {
        return num / tilesize
    }

    // function to convert Point to Rect
    fun getRectFromPoint(p: Point): Rect {
        return Rect(p!!.x * tilesize,
            p!!.y * tilesize,
            (p!!.x + 1) * tilesize,
            (p!!.y + 1 ) * tilesize)
    }

    fun getPointFromPieceLetter(pieceLetter: Char): Point? {
        return when (pieceLetter) {
            'E' -> Point(0, 0)
            'e' -> Point(0, 1)
            'M' -> Point(1, 0)
            'm' -> Point(1, 1)
            'H' -> Point(2, 0)
            'h' -> Point(2, 1)
            'D' -> Point(3, 0)
            'd' -> Point(3, 1)
            'C' -> Point(4, 0)
            'c' -> Point(4, 1)
            'R' -> Point(5, 0)
            'r' -> Point(5, 1)
            else -> null
        }
    }

    /** constant values **/
    companion object {
        const val TILES = 8
    }
}



