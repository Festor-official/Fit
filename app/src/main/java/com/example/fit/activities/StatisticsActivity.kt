package com.example.fit.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.fit.R
import com.example.fit.viewmodel.ExerciseViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsActivity : AppCompatActivity() {

    var barChart: BarChart? = null
    private val exerciseViewModel: ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)
        barChart = findViewById(R.id.chart);
        showBarChart()
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









