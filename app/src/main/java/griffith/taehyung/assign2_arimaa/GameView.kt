package griffith.taehyung.assign2_arimaa

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GameView(context: Context?): View(context){
    private var _context: Context? = context
    private var _attribs: AttributeSet? = null
    private var _defStyleAttr: Int? = null

    private var width: Int? = 0
    private var height: Int?  = 0

    var pieces: Bitmap? = null
    var moveable: Bitmap? = null
    var held: Bitmap? = null

    var pieceset = 0

    var tilesize = 0


    constructor(context: Context, attrs: AttributeSet?) : this(context) {
        _attribs = attrs
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs
    ) {
        _defStyleAttr = defStyleAttr
    }

    init {
//        setBackgroundResource(R.drawable.light_bg)
    }

//    fun setWindowWidth(windowWidth: Int) {
//        width = windowWidth
//        height = windowWidth
//    }

    fun setTileSize(windowWidth: Int) {
        tilesize = windowWidth / TILES
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        width = w
        height = h
    }

    fun drawBoard(canvas: Canvas?, tilesize: Int) {
        val fillPaint = Paint()
        val strokePaint = Paint()

        fillPaint.setStyle(Paint.Style.FILL)
        strokePaint.setStyle(Paint.Style.STROKE)
        strokePaint.setColor(Color.BLACK)

        for (i in 0 until TILES) {
            for (k in 0 until TILES) {
                if(i == 2 &&  k == 2 || i == 5 && k ==2 || i == 2 && k == 5 || i == 5 && k == 5) {
                    fillPaint.setColor(Color.DKGRAY)
                } else
                    fillPaint.setColor(Color.LTGRAY)

                canvas?.drawRect(0f+tilesize * i, 0f+tilesize * k, 0f+tilesize+tilesize*i, 0f+tilesize+tilesize*k, fillPaint)
                canvas?.drawRect(0f+tilesize * i, 0f+tilesize * k, 0f+tilesize+tilesize*i, 0f+tilesize+tilesize*k, strokePaint)
            }
        }
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
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    /** constant values **/
    companion object {
        const val TILES = 8
    }
}


