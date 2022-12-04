package griffith.taehyung.assign2_arimaa

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var _resetbtn: Button? = null
    private var _movebtn: Button? = null
    private var _undobtn: Button? = null
    private var playerStatus: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _resetbtn = findViewById<Button>(R.id.resetbtn)
        _movebtn = findViewById<Button>(R.id.movebtn)
        _undobtn = findViewById<Button>(R.id.undobtn)

        playerStatus = findViewById<TextView>(R.id.playerindicator)

        val gameView: GameView = findViewById(R.id.board)

        gameView.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener{
                override fun onGlobalLayout() {
                    gameView.setTileSize(gameView.width)
                    println("TileSize: " + gameView.tilesize)
                }
            }
        )

        _resetbtn?.setOnClickListener( object: View.OnClickListener {
            override fun onClick(v: View?) {
                gameView?.resetGame()
                setPlayer()
                println("reset the game")
            }
        })

        _movebtn?.setOnClickListener( object: View.OnClickListener {
            override fun onClick(v: View?) {
                gameView?.moves = 4
                if(gameView?.gameStatus == GameView.GameStatus.GOLDTURN)
                    gameView?.gameStatus = GameView.GameStatus.SILVERTURN
                else
                    gameView?.gameStatus = GameView.GameStatus.GOLDTURN

                setPlayer()
                println("End turn")
            }
        })

        _undobtn?. setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                println("Undo")
            }
        })
    }

    fun setPlayer() {
        val gameView = findViewById<View>(R.id.board) as GameView?
        val gamestatus: GameView.GameStatus? = gameView?.gameStatus

        if(gamestatus == GameView.GameStatus.GOLDTURN)
            playerStatus?.setText("Gold")
        else if(gamestatus == GameView.GameStatus.SILVERTURN)
            playerStatus?.setText("Silver")
        else
            playerStatus?.setText("")
    }
}