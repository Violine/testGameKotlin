package com.korovin.alexander.www.orcavspenguin.Adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.korovin.alexander.www.orcavspenguin.GameActivity
import com.korovin.alexander.www.orcavspenguin.Model.GameProcess
import com.korovin.alexander.www.orcavspenguin.Model.Orca
import com.korovin.alexander.www.orcavspenguin.Model.Penguin
import com.korovin.alexander.www.orcavspenguin.R


class RecyclerGamePadAdapter(private var gameProcess: GameProcess)
    : RecyclerView.Adapter<RecyclerGamePadAdapter.ViewHolder>(), GameProcess.OnGameOverListener {

    override fun onGameOver() {
         gameProcess = GameProcess(GameActivity.TABLE_ROW_QUENTITY, GameActivity.TABLE_COLUMN_QUENTITY)
    }

    override fun onBindViewHolder(holder: RecyclerGamePadAdapter.ViewHolder, position: Int) {
        when {
            gameProcess.allCellList[position].animal is Orca -> holder.animalView.setImageResource(R.mipmap.orca)
            gameProcess.allCellList[position].animal is Penguin -> holder.animalView.setImageResource(R.mipmap.tux)
            else -> holder.animalView.setImageResource(R.mipmap.empty)
        }
        holder.animalView.setOnClickListener {
            gameProcess.doLifeCycle(this)
            notifyDataSetChanged()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = gameProcess.allCellList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val animalView: ImageView = itemView.findViewById<TextView>(R.id.animal_image) as ImageView

    }
}