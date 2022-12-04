package griffith.taehyung.assign2_arimaa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var _resetbtn: Button? = null
    private var _movebtn: Button? = null
    private var _backbtn: Button? = null
    private var playerStatus: TextView? = null
    private var gameView: GameView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameView = findViewById(R.id.board)

        _resetbtn = findViewById<Button>(R.id.resetbtn)
        _movebtn = findViewById<Button>(R.id.movebtn)
        _backbtn = findViewById<Button>(R.id.backbtn)

        playerStatus = findViewById<TextView>(R.id.playerindicator)

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

        _backbtn?. setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                println("Back move")
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