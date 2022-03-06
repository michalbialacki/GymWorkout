package com.kkp.gymworkoutplan.Adapters

import android.animation.ObjectAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kkp.gymworkoutplan.R
import com.kkp.gymworkoutplan.WorkoutInter

class PplExecAdapter (var excercises : List<PplExec>, val positionListener : WorkoutInter ) : RecyclerView.Adapter<PplExecAdapter.PplExecViewHolder>() {
    inner class PplExecViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.tvExName)
        val progressBar : ProgressBar = itemView.findViewById(R.id.pbSeriesDone)

        fun getPositionOut(positionListener: WorkoutInter){
            itemView.setOnClickListener {
                ObjectAnimator.ofInt(progressBar,"progress",excercises[position].progress + 25).start()
                positionListener.onExcerciseClicked(position)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PplExecViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exer, parent,false)
        return PplExecViewHolder(view)
    }

    override fun onBindViewHolder(holder: PplExecViewHolder, position: Int) {
        holder.apply{
            title.text = excercises[position].title
            progressBar.progress = excercises[position].progress
            getPositionOut(positionListener)
        }
    }

    override fun getItemCount(): Int {
        return excercises.size
    }

}