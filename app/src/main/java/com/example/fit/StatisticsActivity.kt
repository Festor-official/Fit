package com.example.fit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class StatisticsActivity : AppCompatActivity() {

    var barChart: BarChart? = null
    private lateinit var  exerciseViewModel: ExerciseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        barChart = findViewById(R.id.chart);
        showBarChart()
        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
    }

    private fun showBarChart() {
        val valueList = ArrayList<Double>()
        val entries: ArrayList<BarEntry> = ArrayList()
        val title = "Repeat"



        //input data
        for(i in 0..5) {
            valueList.add(i * 100.1)
        }

        //fit the data into a bar
        for (i in 0 until valueList.size) {
            val barEntry = BarEntry(i.toFloat(), valueList[i].toFloat())
            entries.add(barEntry)
        }
        val barDataSet = BarDataSet(entries, title)
        val data = BarData(barDataSet)
        barChart?.setData(data)
        barChart?.invalidate()
    }



}