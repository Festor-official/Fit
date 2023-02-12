package com.example.fit.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fit.viewmodel.ExerciseViewModel
import com.example.fit.R
import com.example.fit.data.Repeat
import com.example.fit.data.Body
import com.example.fit.viewmodel.BodyViewModel

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChartActivity : AppCompatActivity() {
    private val  exerciseViewModel: ExerciseViewModel by viewModels()
    private val  bodyViewModel: BodyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        val exercise_id = intent.extras?.get("id").toString()

        val chart = findViewById<View>(R.id.chart) as LineChart
        var count = 0
        // x = days y = amount

        val entries: ArrayList<Entry> = ArrayList()

        var allMeasurments = listOf<Body>()
        var allRepeat = listOf<Repeat>()

        if (exercise_id == "body"){
            allMeasurments =  bodyViewModel.allMeasurements
        } else{
            allRepeat = exerciseViewModel.allExercise[exercise_id.toInt()].exercise_list

        }
        if (allRepeat.isNotEmpty()){

            for (exercise in allRepeat){
                entries.add(Entry(count.toFloat(), exercise.sum.toFloat()))
                count+= 1
            }
        } else{
            for (exercise in allMeasurments){
                Log.v("MainActivity","$exercise")
                    entries.add(Entry(count.toFloat(), exercise.forearm.toFloat()))
                    count+= 1
            }
        }


//        entries.add(Entry(1f, 2f))
//        entries.add(Entry(1f, 3f))
//        entries.add(Entry(1f, 5f))

        val dataset = LineDataSet(entries, "График первый")
        val data = LineData(dataset)
        chart.data = data
        chart.invalidate()

    }






}