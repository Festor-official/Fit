package com.example.fit.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fit.R

class PreviousResultAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<PreviousResultAdapter.ResultViewHolder>(){

    val mContext = context
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var bestResultList = mutableListOf<Int>()


    inner class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val bestResultName:TextView = itemView.findViewById(R.id.best_result_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemView = inflater.inflate(R.layout.best_result_sample,parent,false)
        return ResultViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val currentBest = bestResultList[position]
        holder.bestResultName.text = "$currentBest"

    }

    internal fun setResult(bestList:MutableList<Int>){
        this.bestResultList = bestList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return bestResultList.size
    }

}