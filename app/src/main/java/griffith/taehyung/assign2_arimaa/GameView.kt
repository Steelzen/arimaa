package griffith.taehyung.assign2_arimaa

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GameView(context: Context?): View(context){
    private var _context: Context? = context
    private var _attribs: AttributeSet? = null

    private var width: Int? = 0
    private var height: Int?  = 0

    var pieces: Bitmap? = null
    var moveable: Bitmap? = null
    var held: Bitmap? = null

    var pieceset = 0

    var tilesize = 0

    var gameBoard: GameBoard

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
        gameBoard = GameBoard()
    }
    
    fun setTileSize(windowWidth: Int) {
        tilesize = windowWidth / TILES
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
    }

    // set to square view, based on height
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

        drawBoard(canvas, tilesize)
        drawPiece(canvas, tilesize)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    fun drawBoard(canvas: Canvas?, tilesize: Int) {
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

                canvas?.drawRect(0f+tilesize * i, 0f+tilesize * k, 0f+tilesize+tilesize*i, 0f+tilesize+tilesize*k, fillPaint)
                canvas?.drawRect(0f+tilesize * i, 0f+tilesize * k, 0f+tilesize+tilesize*i, 0f+tilesize+tilesize*k, strokePaint)
            }
        }
    }

    fun drawPiece(canvas: Canvas?, tilesize: Int) {
        val paint: Paint = Paint()

        var p: Point? = Point()

        val goldElephant = bitmaps[R.drawable.elephant_gold]!!
        canvas?.drawBitmap(goldElephant, null, Rect(0,0,600,600), paint)
    }

    fun loadBitmaps() {
        imgResourceIDs.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    // function to convert Point to Rect
    fun getRectFromPoint(p: Point): Rect {
        return Rect(p!!.x * tilesize,
            p!!.y * tilesize,
            (p!!.x + 1) * tilesize,
            (p!!.y + 1 ) * tilesize)
    }

    /** constant values **/
    companion object {
        const val TILES = 8
    }
}



