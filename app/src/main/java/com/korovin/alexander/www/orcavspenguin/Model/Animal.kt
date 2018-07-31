package com.korovin.alexander.www.orcavspenguin.Model

import com.korovin.alexander.www.orcavspenguin.GameActivity

import java.util.ArrayList
import java.util.Random
abstract class Animal internal constructor(var position: Int, private var gameProcess: GameProcess) : IAnimalLifeStep {

    companion object {
        const val ORCA_MAX_LIFECYCLE = 3
        const val PINGUIN_PROLIFERATION = 3
        const val ORCA_PROLIFERATION = 8
    }

    var lifeStep: Int = 0
    lateinit var animalCoordinate: Coordinate
    lateinit var emptyCell: ArrayList<Int>

    lateinit var pinguinCell: ArrayList<Int>

    private fun positionToMove(): ArrayList<Coordinate> {
        val positions = ArrayList<Coordinate>()
        val dx = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val dy = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
        for (i in dx.indices) {
            val newRow = this.animalCoordinate.rowCoordinate + dx[i]
            val newColumn = this.animalCoordinate.columnCoordinate + dy[i]
            if (newRow >= 0 && newRow < GameActivity.TABLE_ROW_QUENTITY && newColumn >= 0 && newColumn < GameActivity.TABLE_COLUMN_QUENTITY) {
                positions.add(Coordinate(newRow, newColumn))
            }
        }
        return positions
    }

    protected fun getRandomPosition(emptyCell: ArrayList<Int>): Int {
        val random = Random(System.currentTimeMillis())
        val randomIndex = random.nextInt(emptyCell.size)
        return emptyCell[randomIndex]
    }

    protected fun calculateAnimalAround() {
        val coordinateToMove = positionToMove()
        val orcaCell: ArrayList<Int> = ArrayList()
        emptyCell = ArrayList()
        pinguinCell = ArrayList()

        for (coordinate in coordinateToMove) {
            val pos = coordinate.rowCoordinate * GameActivity.TABLE_COLUMN_QUENTITY + coordinate.columnCoordinate
            val cell: Cell = gameProcess.getCellFromAllCellList(pos)
            when {
                cell.isEmpty -> emptyCell.add(pos)
                cell.animal is Penguin -> pinguinCell.add(pos)
                else -> orcaCell.add(pos)
            }
        }
    }

}
