package com.korovin.alexander.www.orcavspenguin


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.korovin.alexander.www.orcavspenguin.Presentors.GamePresentor
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {

    companion object {
        const val TABLE_ROW_QUENTITY = 15
        const val TABLE_COLUMN_QUENTITY = 10
    }

    lateinit var gamePresentor: GameInteractor.Presentor
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        initUI()
        gamePresentor.onNewGameButtonPressed()
    }

    private fun initUI() {
        setSupportActionBar(toolbar)
        gamePresentor = GamePresentor(this)
        new_game_button.setOnClickListener {
            gamePresentor.onNewGameButtonPressed()
        }
    }

}
