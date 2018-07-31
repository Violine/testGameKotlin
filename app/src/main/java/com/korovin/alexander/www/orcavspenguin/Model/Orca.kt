package com.korovin.alexander.www.orcavspenguin.Model

class Orca(position: Int, private val gameProcess: GameProcess) : Animal(position, gameProcess), IAnimalLifeStep {

    private var proliferation: Int = 0

    override fun doAnimalLifeStep() {

        this.calculateAnimalAround()
        val emptyCell = this.emptyCell
        val penguinCell = this.pinguinCell

        if (this.lifeStep == Animal.ORCA_MAX_LIFECYCLE) {
            gameProcess.getCellFromAllCellList(this.position).animal = null
            //gameProcess.getCellFromAllCellList(this.position).setIsEmpty(true)
            return
        }

        if (this.proliferation == Animal.ORCA_PROLIFERATION && !emptyCell.isEmpty()) {
            gameProcess.proliferationAnimal(getRandomPosition(emptyCell), Orca(getRandomPosition(emptyCell), gameProcess))
            this.proliferation = 0
            this.lifeStep++
            return
        }
        if (!penguinCell.isEmpty()) {
            gameProcess.moveAnimal(getRandomPosition(penguinCell), this.position)
            this.proliferation++
            this.lifeStep = 0
            return
        } else if (!emptyCell.isEmpty()) {
            gameProcess.moveAnimal(getRandomPosition(emptyCell), this.position)
            this.proliferation++
            this.lifeStep++
            return
        }
    }
}