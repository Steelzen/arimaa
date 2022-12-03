package griffith.taehyung.assign2_arimaa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun setPlayer(player: Int) {
        val playerStatus: TextView = findViewById<TextView>(R.id.playerindicator)
        if(player == 1)
            playerStatus.setText("Gold")
        else
            playerStatus.setText("Silver")
    }
}