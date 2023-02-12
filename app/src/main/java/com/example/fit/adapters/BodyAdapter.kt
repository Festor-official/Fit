package com.example.fit.adapters

import android.content.Context
import android.graphics.Color
import android.location.GnssMeasurement
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fit.R
import com.example.fit.data.Body
import java.util.zip.Inflater


class BodyAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<BodyAdapter.BodyViewHolder>() {

    private val inflater = LayoutInflater.from(context)

    private var currentMeasurement = mutableMapOf<String,String>()
    private var previousMeasurement= mutableMapOf<String,String>()

    inner class BodyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val bodyPart:TextView = itemView.findViewById(R.id.body_part)
        val measureDifference:TextView = itemView.findViewById(R.id.measure_difference)

//        val shoulders:TextView = itemView.findViewById(R.id.shoulders)
//        val chest:TextView = itemView.findViewById(R.id.chest)
//        val biceps:TextView = itemView.findViewById(R.id.biceps)
//        val forearm:TextView = itemView.findViewById(R.id.forearm)
//        val wrest:TextView = itemView.findViewById(R.id.wrest)
//        val talia:TextView = itemView.findViewById(R.id.talia)
//        val legs:TextView = itemView.findViewById(R.id.legs)
//        val calf:TextView = itemView.findViewById(R.id.calf)
//        val weight:TextView  =itemView.findViewById(R.id.weight)
//        val height:TextView = itemView.findViewById(R.id.height)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BodyViewHolder {
        val itemView = inflater.inflate(R.layout.body_item,parent,false)
        return BodyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BodyViewHolder, position: Int) {
        val current = currentMeasurement.toList()[position]
        val previous = previousMeasurement.toList()[position]
        if(current.first == "height"){
            holder.bodyPart.text = "${current.first} : ${current.second}CM"
            return
        }

        holder.bodyPart.text = "${current.first}:${current.second} CM"
        val measurement = current.second.toFloat() - previous.second.toFloat()
        holder.measureDifference.text = " ($measurement cm)"
        if(measurement>0){

            holder.measureDifference.setTextColor(Color.argb(255,0,255,0))
        } else{

            holder.measureDifference.setTextColor(Color.argb(255,255,0,0))
        }


    }

    internal fun setMeasurements(curMeasurement: Body,prevMeasurement:Body){
        curMeasurement.let {
            currentMeasurement["shoulders"] = it.shoulders
            currentMeasurement["chest"] = it.chest
            currentMeasurement["biceps"] = it.biceps
            currentMeasurement["forearm"] = it.forearm
            currentMeasurement["wrest"] = it.wrest
            currentMeasurement["talia"] = it.talia
            currentMeasurement["legs"] = it.legs
            currentMeasurement["calf"] = it.caviar
            currentMeasurement["weight"] = it.weight
            currentMeasurement["height"] = it.height
        }

        prevMeasurement.let {
            previousMeasurement["shoulders"] = it.shoulders
            previousMeasurement["chest"] = it.chest
            previousMeasurement["biceps"] = it.biceps
            previousMeasurement["forearm"] = it.forearm
            previousMeasurement["wrest"] = it.wrest
            previousMeasurement["talia"] = it.talia
            previousMeasurement["legs"] = it.legs
            previousMeasurement["calf"] = it.caviar
            previousMeasurement["weight"] = it.weight
            previousMeasurement["height"] = it.height
        }
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int {
        return currentMeasurement.size
    }


}