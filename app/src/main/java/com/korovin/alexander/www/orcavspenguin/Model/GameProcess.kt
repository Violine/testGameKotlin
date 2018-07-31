package com.korovin.alexander.www.orcavspenguin.Model

import com.korovin.alexander.www.orcavspenguin.GameInteractor
import java.util.ArrayList
import java.util.HashSet
import java.util.Random

class GameProcess(private val rowCount: Int, private val columnCount: Int) : GameInteractor.GameModel {

    interface OnGameOverListener {
        fun onGameOver()
    }

    private val animalCellPercent = 55
    private lateinit var allOrcaList: ArrayList<Orca>
    private lateinit var allPengiunList: ArrayList<Penguin>

    var allCellList: ArrayList<Cell> = ArrayList()

    init {
        initCell()
        setAnimalToCell(allCellList.size)
    }

    fun getCellFromAllCellList(pos: Int): Cell {
        return allCellList[pos]
    }

    private fun initCell() {
        for (i in 0 until rowCount) {
            for (j in 0 until columnCount) {
                val cell = Cell(i, j)
                allCellList.add(cell)
            }
        }
    }

    private fun getRandomCell(celQuantity: Int): Set<Int> {
        val animalQuantity = Math.floor((animalCellPercent * celQuantity / 100).toDouble()).toInt()
        val animalCells = HashSet<Int>()
        val random = Random(System.currentTimeMillis())
        while (animalCells.size < animalQuantity) {
            animalCells.add(random.nextInt(celQuantity))
        }
        return animalCells
    }

    private fun setAnimalToCell(cellCounter: Int) {
        val animalCell = getRandomCell(cellCounter)
        val allCell = HashSet<Int>()
        for (i in 0 until cellCounter) {
            allCell.add(i)
        }
        allCell.removeAll(animalCell)
        for (emptyCellPosition in allCell) {
            allCellList[emptyCellPosition].animal = null
        }
        allOrcaList = ArrayList()
        allPengiunList = ArrayList()
        val animalArray = animalCell.toTypedArray()
        shuffleArray(animalArray)
        for (i in animalArray.indices) {
            if (i % 5 == 0) {
                val orca = Orca(animalArray[i], this)
                allOrcaList.add(orca)
                allCellList[animalArray[i]].animal = orca
            } else {
                val penguin = Penguin(animalArray[i], this)
                allPengiunList.add(penguin)
                allCellList[animalArray[i]].animal = penguin
            }
        }
    }

    private fun shuffleArray(ar: Array<Int>) {
        val rnd = Random()
        for (i in ar.size - 1 downTo 1) {
            val index = rnd.nextInt(i + 1)
            val a = ar[index]
            ar[index] = ar[i]
            ar[i] = a
        }
    }

    fun moveAnimal(positionForMove: Int, thisPosition: Int) {
        allCellList[positionForMove].animal = allCellList[thisPosition].animal
        allCellList[positionForMove].setIsModify(true)
        allCellList[thisPosition].animal = null
    }

    fun proliferationAnimal(positionForProliferation: Int, animal: Animal) {
        allCellList[positionForProliferation].animal = animal
        allCellList[positionForProliferation].setIsModify(true)
    }

    fun doLifeCycle(listener: OnGameOverListener) {
        var nullCellCounter = 0
        var penguinCellCounter = 0
        for (i in 0 until allCellList.size) {
            if (allCellList[i].animal == null) {
                nullCellCounter++
                if (nullCellCounter == allCellList.size) {
                    listener.onGameOver()
                    return
                }
                continue
            } else if (allCellList[i].isModify) {
                continue
            } else {
                if (allCellList[i].animal is Penguin) {
                    penguinCellCounter++
                    if (penguinCellCounter == allCellList.size) {
                        listener.onGameOver()
                        return
                    }
                }
                val coordinate = Coordinate(allCellList[i].rowCoordinats, allCellList[i].columnCoordinats)
                allCellList[i].animal!!.animalCoordinate = coordinate
                allCellList[i].animal!!.position = i
                allCellList[i].animal!!.doAnimalLifeStep()
            }
        }
        for (cell in allCellList) {
            cell.setIsModify(false)
        }
    }
}
