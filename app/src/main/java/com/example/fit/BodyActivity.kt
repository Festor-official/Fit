package com.example.fit


import android.content.Intent
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class BodyActivity : AppCompatActivity() {
    private lateinit var  bodyViewModel: BodyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body)

        bodyViewModel = ViewModelProvider(this).get(BodyViewModel::class.java)
        var res:Float = 0F

        val chartButton = findViewById<ImageView>(R.id.charts2)
        chartButton.setOnClickListener {
            val intent = Intent(this,ChartActivity::class.java)
            intent.putExtra("id","body")
            startActivity(intent)
        }

        val measurement = bodyViewModel.lastMeasurement
        var measurement2  = bodyViewModel.previous()
        if(measurement != null){
            if (measurement2==null){
                measurement2 = measurement
            }
            measurement
            val shoulders = findViewById<TextView>(R.id.shoulders)
            res  = measurement.shoulders.toFloat()  - measurement2.shoulders.toFloat()
            shoulders.text  = "shoulders:" + measurement.shoulders + "cm" +  "($res cm)"

            val biceps = findViewById<TextView>(R.id.biceps)
            res  = measurement.biceps.toFloat()  - measurement2.biceps.toFloat()
            biceps.text  ="biceps:" + measurement.biceps+ "cm"+  "($res cm)"

            val chest = findViewById<TextView>(R.id.chest)
            res  = measurement.chest.toFloat()  - measurement2.chest.toFloat()
            chest.text  ="chest:" + measurement.chest+ "cm"+  "($res cm)"

            val forearm = findViewById<TextView>(R.id.forearm)
            res  = measurement.forearm.toFloat() - measurement2.forearm.toFloat()
            forearm.text  ="forearm:" + measurement.forearm+ "cm"+  "($res cm)"

            val wrest = findViewById<TextView>(R.id.wrest)
            res  = measurement.wrest.toFloat()  - measurement2.wrest.toFloat()
            wrest.text  ="wrest:" + measurement.wrest+ "cm"+  "($res cm)"

            val talia = findViewById<TextView>(R.id.talia)
            res  = measurement.talia.toFloat()  - measurement2.talia.toFloat()
            talia.text  ="talia:" + measurement.talia+ "cm"+  "($res cm)"

            val legs = findViewById<TextView>(R.id.legs)
            res  = measurement.legs.toFloat()  - measurement2.legs.toFloat()
            legs.text  ="legs:" + measurement.legs+ "cm"+  "($res cm)"

            val caviar = findViewById<TextView>(R.id.caviar)
            res  = measurement.caviar.toFloat()  - measurement2.caviar.toFloat()
            caviar.text  ="caviar:" + measurement.caviar+ "cm"+  "($res cm)"

            val weight = findViewById<TextView>(R.id.weight)
            res  = measurement.weight.toFloat()  - measurement2.weight.toFloat()
            weight.text  ="weight:" + measurement.weight + "kg"+  "($res kg)"

            val height = findViewById<TextView>(R.id.height)
            height.text  ="height:" + measurement.height+ "cm"
        }



        val imageView = findViewById<ImageView>(R.id.image_body)

        val assets: AssetManager = applicationContext.assets
        val reader =   assets.open("images/30662.png")
        val bitmap = BitmapFactory.decodeStream(reader)
        imageView.setImageBitmap(bitmap)

        findViewById<ImageView>(R.id.add).setOnClickListener {
            val fm = supportFragmentManager
            val bodyFragment = BodyFragment()
            bodyFragment.show(fm,"AlertDialog")

        }



    }

}