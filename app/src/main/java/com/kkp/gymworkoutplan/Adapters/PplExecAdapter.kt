package com.kkp.gymworkoutplan.Adapters

import android.animation.ObjectAnimator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kkp.gymworkoutplan.MainActivity
import com.kkp.gymworkoutplan.R
import com.kkp.gymworkoutplan.WorkoutInter
import com.kkp.gymworkoutplan.WorkoutViewModel
import com.kkp.gymworkoutplan.databinding.ItemExerBinding

class PplExecAdapter (var excercises : List<PplExec>, val positionListener : WorkoutInter ) : RecyclerView.Adapter<PplExecAdapter.PplExecViewHolder>() {

    inner class PplExecViewHolder(val binding: ItemExerBinding) : RecyclerView.ViewHolder(binding.root){
        fun getPositionOut(positionListener: WorkoutInter){
            itemView.setOnClickListener {
                ObjectAnimator.ofInt(binding.pbSeriesDone
                    ,"progress"
                    ,excercises[position].progress + 25)
                    .start()
                positionListener.onExcerciseClicked(position)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PplExecViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_exer, parent,false)
        return PplExecViewHolder(ItemExerBinding.inflate(LayoutInflater
            .from(parent.context)
            ,parent
            ,false))
    }

    override fun onBindViewHolder(holder: PplExecViewHolder, position: Int) {
        holder.binding.apply {
            tvExName.text = excercises[position].title
            pbSeriesDone.progress = excercises[position].progress
            tvWeigth.text = "${excercises[position].mass}"
        }
        holder.apply {
            getPositionOut(positionListener)
        }
    }

    override fun getItemCount(): Int {
        return excercises.size
    }

}