package com.korovin.alexander.www.orcavspenguin.Model

class Cell internal constructor(val rowCoordinats: Int, val columnCoordinats: Int) {

    var animal: Animal? = null

    var isModify: Boolean = false

    val isEmpty: Boolean
        get() = this.animal == null

    fun setIsModify(bool: Boolean) {
        this.isModify = bool
    }

}