package com.korovin.alexander.www.orcavspenguin.Presentors

import android.support.v7.widget.GridLayoutManager
import com.korovin.alexander.www.orcavspenguin.Adapters.CustomRecyclerViewDecorator
import com.korovin.alexander.www.orcavspenguin.Adapters.RecyclerGamePadAdapter
import com.korovin.alexander.www.orcavspenguin.GameActivity
import com.korovin.alexander.www.orcavspenguin.GameInteractor
import com.korovin.alexander.www.orcavspenguin.Model.GameProcess
import kotlinx.android.synthetic.main.activity_game.*

class GamePresentor(private val gameActivity: GameActivity) : GameInteractor.Presentor, GameProcess.OnGameOverListener {


    override fun onNewGameButtonPressed() {
        val gameProcess = GameProcess(GameActivity.TABLE_ROW_QUENTITY, GameActivity.TABLE_COLUMN_QUENTITY)
        val recyclerViewAdapter = RecyclerGamePadAdapter(gameProcess)
        gameActivity.recycler_game_pad.layoutManager = GridLayoutManager(gameActivity, GameActivity.TABLE_COLUMN_QUENTITY)
        gameActivity.recycler_game_pad.addItemDecoration(CustomRecyclerViewDecorator(0))
        gameActivity.recycler_game_pad.adapter = recyclerViewAdapter
        gameActivity.game_activity.setOnClickListener {
            onGamePadClick(gameProcess)
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun onGamePadClick(gameProcess: GameProcess) {
        gameProcess.doLifeCycle(this)

    }

    override fun onGameOver() {
        onNewGameButtonPressed()
    }
}