package com.korovin.alexander.www.orcavspenguin

import com.korovin.alexander.www.orcavspenguin.Model.GameProcess

interface GameInteractor {
    interface GameModel

    interface Presentor {
        fun onNewGameButtonPressed()
        fun onGamePadClick(gameProcess: GameProcess)
    }
}