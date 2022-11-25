package griffith.taehyung.assign2_arimaa

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class GameView(context: Context?): View(context){
    private var _context: Context? = context
    private var _attribs: AttributeSet? = null
    private var _defStyleAttr: Int? = null
    var pieces: Bitmap? = null
    var moveable: Bitmap? = null
    var held: Bitmap? = null

    var pieceset = 0

    var tilesize = 0

    //size of background image, currently use as a SQUARE
    private var width: Int? = 0
    private var height: Int?  = 0

    //tie background image choice to user selection in preferences
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
        setBackgroundResource(R.drawable.light_bg)
    }

    fun setWindowWidth(windowWidth: Int) {
        width = windowWidth
        height = windowWidth
    }

    //set to square view, based on height
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (widthMeasureSpec < heightMeasureSpec)
            super.onMeasure(
            widthMeasureSpec,
            widthMeasureSpec
        ) else super.onMeasure(heightMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    /** constant values **/
    companion object {
        const val TILES = 8
    }
}