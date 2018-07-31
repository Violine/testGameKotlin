package com.korovin.alexander.www.orcavspenguin.Model

class Penguin(position: Int, private val gameProcess: GameProcess) : Animal(position, gameProcess), IAnimalLifeStep {
    override fun doAnimalLifeStep() {
        this.calculateAnimalAround()
        val emptyCell = this.emptyCell
        if (!emptyCell.isEmpty()) {
            val positionForMove = getRandomPosition(emptyCell)
            if (this.lifeStep == Animal.PINGUIN_PROLIFERATION) {
                this.lifeStep = 0
                gameProcess.proliferationAnimal(positionForMove, Penguin(positionForMove, gameProcess))
            } else {
                gameProcess.moveAnimal(positionForMove, this.position)
            }
            this.lifeStep++
        }
    }
}